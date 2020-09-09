import instance from '../config/axios';

class UserApi {

    static userRegistration(data) {

        let body = {
            'mobile_number': data.mobileNumber,
            'first_name': data.firstName,
            'last_name': data.lastName,
            'date_of_birth': data.dateOfBirth,
            'gender': data.gender,
            'email': data.email,
        }

        return new Promise((resolve, reject)=>{
            instance.post('/api/v1/user', body).then(response => {
                resolve(response);               
            }).catch(error=>{
                reject(error);
            });
        })
    }
     
}

export default UserApi;