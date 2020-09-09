import React, {Component} from 'react';
import './App.css';
import { withRouter } from "react-router-dom";
import PropTypes from 'prop-types';

class App extends Component {
  render() {
    return (
      <div className="body">
        {this.props.children}
      </div>
    );
  }
}

App.propTypes = {
  children: PropTypes.element,
  location: PropTypes.object,
  "location.pathname": PropTypes.string
};

export default withRouter(App);