import React, { Component } from 'react';
import './index.css';
import { Link } from 'react-router-dom';

class FooterWithLogin extends Component {
  render() {
    return (
      <footer className="footer-login">
        <Link to='/login'>
          <button type="button">Login</button>
        </Link>
      </footer>
    );
  }
}

export default FooterWithLogin;
