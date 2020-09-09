import React, {Component} from 'react';
import './index.css';

class Popup extends Component {

    render(){
        const className = this.props.open === true ? "popuptext show" : "popuptext";

        return (
            <div className="popup" >
                <span className={className} id="myPopup">{this.props.message}</span>
            </div>
        )
    }
}

export default Popup;