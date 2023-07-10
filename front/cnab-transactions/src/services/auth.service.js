import axios from "axios";

const API_URL = "http://localhost:8080/api/";

class AuthService { 
  login(username, password) {
    return axios
      .post(API_URL + "login", {
        username,
        password
      })
      .then(response => {
        if (response.data.user.token) {
          localStorage.setItem("user", JSON.stringify(response.data));
        }

        return response.data;
      });
  }

  logout() {
    localStorage.removeItem("user");
  }

  register(firstName, lastName, email, password) {
    return axios.post(API_URL + "user", {
      firstName,
      lastName,
      username: email,
      password
    });
  }

  getCurrentUser() {
    var element = localStorage.getItem('user')
    if(element) {
      var user = JSON.parse(localStorage.getItem('user'))
      if (user)
        return user.user;
    }

    return null
  }
}

export default new AuthService();
