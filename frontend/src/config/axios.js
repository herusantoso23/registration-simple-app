import axios from 'axios';
import ErrorHandler from '../commons/errorHandler';

const instance = axios.create({
    baseURL:"http://localhost:8080",
})

instance.interceptors.request.use(function (config) {
    return config;
}, function (error) {
     return Promise.reject(ErrorHandler.enhanceError(error));
})

instance.interceptors.response.use( (response) => {
    return response;
}, (error) => {
    return Promise.reject(ErrorHandler.enhanceError(error));
});

export default instance;