import axios from 'axios';

import AuthHeader from "./auth-header";


const API_URL = 'http://localhost:8080/api/';

class UserService {
  getAllTransactions() {
    return axios.get(API_URL + 'cnab/list', { headers: AuthHeader.authHeader() });
  }

  filterTransactions(filter) {
    return axios.get(API_URL + 'cnab/find?ownerName=' + filter, { headers: AuthHeader.authHeader() });
  }

  fileUpload(file) {
    const formData = new FormData();
    formData.append("file", file)

    return axios
      .post(API_URL + "cnab/upload", formData, {headers: AuthHeader.authHeaderFileUpload() })
      .then(response => {
        return response.data
      }); 
  }

  getUserBoard() {
    return axios.get(API_URL + 'user', { headers: AuthHeader.authHeader() });
  }

  getModeratorBoard() {
    return axios.get(API_URL + 'mod', { headers: AuthHeader.authHeader() });
  }

  getAdminBoard() {
    return axios.get(API_URL + 'admin', { headers: AuthHeader.authHeader() });
  }
}

export default new UserService();
