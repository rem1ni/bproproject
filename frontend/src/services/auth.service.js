import axios from "axios";

const API_URL = "http://localhost:8080/bpro/auth/";

class AuthService {
  login(username, password) {
    return axios
      .post(API_URL + "signin", {
        username,
        password
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem("user", JSON.stringify(response.data));
        }
        return response.data;
      });
  }
   ref(iduser){
    axios.post("http://localhost:8080/info", {
      iduser
    })
        .then(response => {
          localStorage.setItem("myKey",JSON.stringify(response.data));
        })}
  logout() {
    localStorage.removeItem("user");
  }

  register(username, email, password) {
    return axios.post(API_URL + "signup", {
      username,
      email,
      password
    });
  }

  getCurrentUser() {
    return JSON.parse(localStorage.getItem('user'));
  }
}

export default new AuthService();
