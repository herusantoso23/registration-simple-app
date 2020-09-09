class ErrorHandler {

    static of(err){
        let error = {};
        try {
            error = JSON.parse(err);
            console.log(error);
        } catch (e){
            console.log(e);
        }

        if(error.status === 400){
            return error.message;
        } else {
            return "Ooops something wrong, please contact the administrator";
        }
    }

    static enhanceError(error) {
        let msg = {};
        
        try {
            msg = {
                status : error.response.status,
                error : error.response.data.error,
                error_description : error.response.data.error_description,
                message : error.response.data.message,
                result : error.response.data.result,
            }
        } catch (e){
            console.log(e);
        }

        return JSON.stringify(msg);
    };

}

export default ErrorHandler;