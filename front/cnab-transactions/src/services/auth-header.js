class HeaderService {

  authHeader() {
    const element = localStorage.getItem('user')
    if(element) {
      const user = JSON.parse(localStorage.getItem('user')).user;

      if (user && user.token) {
        return {Authorization: 'Bearer ' + user.token}; // for Spring Boot back-end
      } else {
        return {};
      }
    }

    return {}
  }

  authHeaderFileUpload() {
    const user = JSON.parse(localStorage.getItem('user')).user;
  
    if (user && user.token) {
      return { 
        Authorization: 'Bearer ' + user.token, 
        'content-type': "multipart/form-data", 
      };
    } else {
      return {};
    }
  }


}

export default new HeaderService();


