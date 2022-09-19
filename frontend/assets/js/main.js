window.onload = function () {
  //authenticationChecking();

  document.getElementById('showSignin').onclick = showSigninForm;
  
  document.getElementById('showSignup').onclick = showSignupForm;
  if (document.getElementById('signout')) {
    document.getElementById('signout').onclick = doSignout;
  }
};

const host = "http://localhost:4040";

async function showProducts() {
  const products = await fetch(`${host}/products`, {
    method: 'GET',
    cache: 'no-cache',
    headers: headersGeneration()
  }).then(response => response.json());

  if (products) {
    const contentDiv = document.getElementById('main-content');
    contentDiv.classList = "";
    contentInfoDisplay(false);

    const title = document.createElement('h2');
    title.innerHTML = "Product list";
    contentDiv.appendChild(title);

    const childDiv = document.createElement('div');
    childDiv.classList = "bd-example-snippet bd-code-snippet";
    childDiv.id = 'products';
    childDiv.innerHTML = `
    <div class="bd-example">
      <table class="table table-sm table-bordered">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Price</th>
            <th scope="col">Rating</th>
            <th scope="col">Review</th>
          </tr>
        </thead>
        <tbody id="tbody"></tbody>
      </table>
    </div>
    `;
    
    contentDiv.appendChild(childDiv);

    const tbody = document.getElementById('tbody');

    products.forEach(prod => {
      const row = document.createElement('tr');
      const reviews = prod.reviews;
      let review = '';
      reviews.forEach(r => {
        review += `${r.user.firstName} ${r.user.lastName} comment: ${r.comment} <br/>`;
      })
      row.innerHTML = `
      <tr>
        <th scope="row">${prod.id}</th>
        <td>${prod.name}</td>
        <td>${prod.price}</td>
        <td>${prod.rating}</td>
        <td>${review}</td>
      </tr>
      `;

      tbody.appendChild(row);
    });
    
  }
}
function authenticationChecking() {
  if (sessionStorage.getItem('accessToken')) {
    fetch(`${host}/users/authentication`, {
    method: 'GET',
    cache: 'no-cache',
    headers: headersGeneration()
  }).then(response => response.json())
    .then(obj => {
      //show
    }).catch(err => {
      console.log(err);
    });
  } else {
    // show
    document.getElementById('loginForm').classList = 'login';
    document.getElementById('welcome').classList = 'login hide';
    //show blank body
    renderWelcomeStore();
  }
}
function doSignout() { 
  sessionStorage.clear();
  location.reload();
}
function showSigninForm() { 
  const contentDiv = document.getElementById('main-content');
  contentInfoDisplay(false);
  formRemove('signup-form');
  //show form
  contentDiv.classList = "form-signin w-100 m-auto";
  
  const form = document.createElement('form');
  form.id = 'signin-form';
  form.innerHTML = `
  <h1 class="h3 mb-3 fw-normal">Please sign in</h1>
    <div class="form-floating">
      <input type="email" class="form-control" id="email" placeholder="Email">
      <label for="email">Email</label>
    </div>
    <div class="form-floating">
      <input type="password" class="form-control" id="password" placeholder="Password">
      <label for="password">Password</label>
    </div>
    
    <div class="checkbox mb-3">
      <label>
        <input type="checkbox" value="1"> Remember me
      </label>
    </div>
    <div class="form-floating">
      <span class="error" id="loginError"></span>
    </div>
    <button class="w-100 btn btn-lg btn-primary" type="button" id="signin">Sign in</button>
  `;
  contentDiv.appendChild(form);
  document.getElementById('signin').onclick = doSignin;
}
async function doSignin() {
  let email = document.getElementById('email').value;
  let password = document.getElementById('password').value;
  
  if (email && password) {
    let res = await fetch(`${host}/uaa/signin`, {
      method: 'POST',
      cache: 'no-cache',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ email: email, password: password })
    }).then(response => response.json());

    if (res && res.accessToken) {
      sessionStorage.setItem('accessToken', res.accessToken);
      document.getElementById('signin-form').remove();
      displayHeaderNav(true);
      //hide welcome store
      displayWelcomeStore(false);
      //hide sign-btn
      displaySignBtn(false);
      //add header
      showLoginUser();

      showProducts();
    } else {
      document.getElementById('loginError').innerHTML = "There is something wrong!";
    }

  } else {
    document.getElementById('loginError').innerHTML = 'Please input email and password!'
  }
}
function displayWelcomeStore(show) {
  const div = document.getElementById('no-login-header');
  if (show) {
    div.classList = 'nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0';
  } else {
    div.classList = 'nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0 hide';
  }
}
function displayHeaderNav(show) {
  const div = document.getElementById('header-nav');
  if (show) {
    div.classList = 'nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0';
  } else {
    div.classList = 'nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0 hide';
  }
}
function displaySignBtn(show) {
  const div = document.getElementById('sign-btn');
  if (show) {
    div.classList = 'col-md-3 text-end';
  } else {
    div.classList = 'col-md-3 text-end hide';
  }
}
function showLoginUser() {
  const headerDiv = document.getElementById('header');

  const userDetail = document.createElement('div');
  userDetail.classList = "dropdown text-end";
  userDetail.innerHTML = `
  <a href="javascript:void(0);" class="d-block link-dark text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
    <img src="./assets/images/female.jpg" alt="mdo" width="32" height="32" class="rounded-circle">
  </a>
  <ul class="dropdown-menu text-small">
    <li><a class="dropdown-item" href="javascript:void(0);">Profile</a></li>
    <li><hr class="dropdown-divider"></li>
    <li><a class="dropdown-item" href="javascript:void(0);" id="signout">Sign out</a></li>
  </ul>
  `;
  headerDiv.appendChild(userDetail);
  document.getElementById('signout').onclick = doSignout;
}

function showSignupForm() {
  const contentDiv = document.getElementById('main-content');
  contentDiv.classList = "form-signup w-100 m-auto";
  contentInfoDisplay(false);
  formRemove('signin-form');
  const form = document.createElement('form');
  form.id = 'signup-form';
  form.innerHTML = `
  <h1 class="h3 mb-3 fw-normal">Sign up for free</h1>
          <div class="form-floating">
            <input type="email" class="form-control" id="email" placeholder="Email">
            <label for="email">Email</label>
          </div>

          <div class="form-floating">
            <input type="password" class="form-control" id="password" placeholder="Password">
            <label for="password">Password</label>
          </div>

          <div class="form-floating">
            <input type="text" class="form-control" id="fname" placeholder="First Name">
            <label for="fname">First Name</label>
          </div>

          <div class="form-floating">
            <input type="text" class="form-control" id="lname" placeholder="Last Name">
            <label for="lname">Last Name</label>
          </div>

          <div class="form-floating">
            <h5>Gender</h5>
            <div class="form-check">
              <input type="radio" name="gender" class="form-check-input" value="female">
              <label class="form-check-label" for="female">Female</label>
            </div>
            <div class="form-check">
              <input type="radio" name="gender" class="form-check-input" value="male">
              <label class="form-check-label" for="male">Male</label>
            </div>
          </div>

          <div class="form-floating">
            <input type="text" class="form-control" id="street" placeholder="Street">
            <label for="street">Street</label>
          </div>

          <div class="form-floating">
            <input type="text" class="form-control" id="city" placeholder="City">
            <label for="city">City</label>
          </div>

          <div class="form-floating">
            <input type="text" class="form-control" id="zip" placeholder="Zip">
            <label for="zip">Zip</label>
          </div>

          <div class="form-floating">
            <span class="error" id="loginError"></span>
          </div>

          <button class="w-100 mb-2 btn btn-lg btn-primary" type="button" id="signup">Sign up</button>
          <small class="text-muted">By clicking Sign up, you agree to the terms of use.</small>
  `;
  contentDiv.appendChild(form);
  document.getElementById('signup').onclick = doSignup;
}
async function doSignup() { 
  let email = document.getElementById('email').value;
  let password = document.getElementById('password').value;
  let fname = document.getElementById('fname').value;
  let lname = document.getElementById('lname').value;
  let gender = document.getElementsByName('gender');

  for(i = 0; i < gender.length; i++) {
    if (gender[i].checked) {
      gender = gender[i].value;
    }
  }

  let street = document.getElementById('street').value;
  let city = document.getElementById('city').value;
  let zip = document.getElementById('zip').value;

  let newUser = {
    email: email, password: password,
    fname: fname, lname: lname, gender: gender,
    street: street, city: city, zip: zip
  };
  
  if (email && password) {
    let res = await fetch(`${host}/uaa/signup`, {
      method: 'POST',
      cache: 'no-cache',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(newUser)
    }).then(response => response.json());

    if (res && res.id) {
      document.getElementById('signup-form').remove();
      const div = document.createElement('p');
      div.classList = "h1";
      div.id = "signup-inform";
      div.innerHTML = 'Signup is successful!';

      document.getElementById('main-content').appendChild(div);
    } else {
      document.getElementById('loginError').innerHTML = "There is something wrong!";
    }

  } else {
    document.getElementById('loginError').innerHTML = 'Please input email and password!'
  }
}

function contentInfoDisplay(show) {
  const content = document.getElementById('no-login-content');
  if (show) {
    content.classList = "show";
  } else {
    content.classList = "hide";
  }
}

function formRemove(formId) {
  const form = document.getElementById(formId);
  
  if (form) {
    form.remove();
  } 
}


function headersGeneration() {
  return {
    'Content-Type': 'application/json',
    'authorization': generateAuthenString()
  };
}
function generateAuthenString() {
  return 'Bearer ' + sessionStorage.getItem('accessToken');
}