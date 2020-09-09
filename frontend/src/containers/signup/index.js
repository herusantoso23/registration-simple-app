import React, { Component } from 'react';
import './index.css';
import Footer from '../../components/Footer';
import FooterWithLogin from '../../components/Footer/Login';
import moment from 'moment';
import UserApi from '../../api/userApi';
import Popup from '../../components/Popup';
import ErrorHandler from '../../commons/errorHandler';

class SignUp extends Component {

    constructor(props) {
        super(props);
        this.state = {
            user : {
                mobileNumber : "",
                firstName : "",
                lastName : "",
                email : "",
                gender :"MALE",
            },
            dateOfBirth: {
                date : '',
                month: '',
                year: '',
            },
            loading: false,
            isRegistrationSuccess: false,
            error: {
                show: false,
                message: ""
            }
        }
    }

    getMonths = () => {
        var months = [];
        for(var i = 0; i < 12 ; i++){
          months[i] = i + 1;
        }
        return months;
      }
    
    getDates = () => {
        var days = [];
        for(var i = 0; i < 31 ; i++){
            days[i] = i + 1;
        }
        return days;
    }
    
    getYears = () => {
        var years = [];
        for(var i = 0; i < 50 ; i++){
            years[i] = i + 1970;
        }
        return years;
    }

    getDateOfBirth = () => {
        const dateOfBirth = this.state.dateOfBirth;

        var notEmpty = dateOfBirth.year !== '' && dateOfBirth.month !== '' && dateOfBirth.date !== '';

        if(notEmpty === false) {
            this.resetDateState();
            return null;
        }

        var dateString = dateOfBirth.year + "-" + dateOfBirth.month + "-" + dateOfBirth.date;
        var date = null;
        try {
            date = new Date(dateString);
            return date;
        } catch (e){
            this.resetDateState();
            return null;
        }
    }

    dateToString= (date) => {
        if(date === null) return null;

        try {
            return moment(date).format('YYYY-MM-DD');
        } catch (e){
            console.log(e);
            return null;
        }
    }

    resetDateState = () => {
        this.setState({
            dateOfBirth: {
                date : '',
                month: '',
                year: '',
            }
        })
    }

    handleGenderChange = (event) => {
        this.setState({
            user: { 
                ...this.state.user, 
                gender: event.target.value
            }
        }) 
    }

    onSubmit = (e) => {
        e.preventDefault();
        const dateOfBirth = this.getDateOfBirth();
        this.setState({
            user: {
                ...this.state.user,
                dateOfBirth: this.dateToString(dateOfBirth),
            },
            loading: true,
        },()=>{
            this.userRegistration(this.state.user);
        })
        return false;
    }

    userRegistration = (data) => {
        UserApi.userRegistration(data).then((response) => {
            console.log(response)
            this.setState({
                loading: false,
                isRegistrationSuccess: true,
                error: {
                    show: false,
                    message: ''
                }
            })
        }).catch((error) => {
            const message = ErrorHandler.of(error);
            this.setState({
                loading: false,
                isRegistrationSuccess: false,
                error: {
                    show: true,
                    message: message
                }
            });
        })
    }

    render() {
        const data = this.state.user;
        const dateOfBirth = this.state.dateOfBirth;

        const formWrapperClass = this.state.loading || this.state.isRegistrationSuccess ? 'form-wrapper disabled' : 'form-wrapper';

        return (
            <div className="wrapper">
                <div className={formWrapperClass}>
                    <p className="title">Registration</p>
                    <form onSubmit={this.onSubmit}>
                        <Popup open={this.state.error.show} message={this.state.error.message}/>
                        <div id="form-wrapper">
                            <div className="field-wrapper">
                                <input type="text" placeholder="Mobile Number" 
                                    value={data.mobileNumber} onChange={e => this.setState({user : {...this.state.user, mobileNumber: e.target.value}})}
                                />
                            </div>
                            <div className="field-wrapper">
                                <input type="text" placeholder="Firstname" 
                                    value={data.firstName} onChange={e => this.setState({user : {...this.state.user, firstName: e.target.value}})}
                                />
                            </div>
                            <div className="field-wrapper">
                                <input type="text" placeholder="Lastname" 
                                    value={data.lastName} onChange={e => this.setState({user : {...this.state.user, lastName: e.target.value}})}/>
                            </div>
                            <div className="date-wrapper" >
                                <label htmlFor="date-of-birth">Date of birth</label>
                                <div id="date-of-birth">
                                <select value={dateOfBirth.month} onChange={(e) => this.setState({dateOfBirth: {...this.state.dateOfBirth, month: e.target.value }})}>
                                    <option value='' disabled>Month</option>
                                    {this.getMonths().map((value) => <option value={value} key={value}>{value}</option>)}
                                </select>
                                <select value={dateOfBirth.date} className="date-select" onChange={(e) => this.setState({dateOfBirth: {...this.state.dateOfBirth, date: e.target.value }})}>
                                    <option value='' disabled>Date</option>
                                    {this.getDates().map((value) => 
                                        <option value={value} key={value} >{value} </option>)
                                    }
                                </select>
                                <select value={dateOfBirth.year} onChange={(e) => this.setState({dateOfBirth: {...this.state.dateOfBirth, year: e.target.value }})}>
                                    <option value='' disabled>Year</option>
                                    {this.getYears().map((value) => 
                                        <option value={value} key={value} >{value}</option>)
                                    }
                                </select>
                                </div>
                            </div>
                            <div className="gender-wrapper">
                                <label>
                                    <input type="radio"
                                        value="MALE" 
                                        checked={data.gender === "MALE"}
                                        onChange={this.handleGenderChange}
                                        /> Male
                                </label>
                                <label>
                                    <input type="radio"
                                        value="FEMALE" 
                                        checked={data.gender === "FEMALE"}
                                        onChange={this.handleGenderChange}
                                        /> Female
                                </label>
                            </div>
                            <div className="field-wrapper">
                                <input type="text" placeholder="Email" name="email"
                                    value={data.email} onChange={e => this.setState({user : {...this.state.user, email: e.target.value}})}/>
                            </div>
                            <button type="submit">Register</button>
                        </div>
                    </form>
                </div>
                {
                    this.state.isRegistrationSuccess ? <FooterWithLogin/> : <Footer />
                }
            </div>
        );
    }
}

export default SignUp;