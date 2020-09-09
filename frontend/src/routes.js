import React from 'react';
import { Route, Switch, Redirect } from 'react-router-dom';

import App from './App';
import Signup from './containers/signup';
import Login from './containers/login';

export default function Routes() {

  return (
    <App>
      <Switch>
        <Route exact path='/' name='Register' component={Signup} />
        <Route exact path='/registration' name='Register' component={Signup} />
        <Route exact path='/login' name='Login' component={Login} />
        <Redirect to="/404" />
      </Switch>
    </App>
  )
};
