import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import GroupEdit from './GroupEdit';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import GroupList from './GroupList';
import { CookiesProvider } from 'react-cookie'; 


class App extends Component {
  render() {
    return (
      <CookiesProvider>
        <Router>
        <Switch>
            <Route path='/' exact={true} component={Home}/>
            <Route path='/groups/:id' component={GroupEdit}/>
            <Route path='/groups' exact={true} component={GroupList}/>
            </Switch>              
        </Router>
      </CookiesProvider>
    )
  }
}

export default App;

// </Switch>

// TODO OLD POC BELOW HERE................
/*import logo from './logo.svg';
import './App.css';

class App extends Component {
  state = {
    isLoading: true,
    groups: []
  };

  async componentDidMount() {
    const response = await fetch('/api/groups');
    const body = await response.json();
    this.setState({ groups: body, isLoading: false });
  }

  render() {
    const {groups, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <div className="App-intro">
            <h2>JUG List</h2>
            {groups.map(group =>
              <div key={group.id}>
                {group.name}
              </div>
            )}
          </div>
        </header>
      </div>
    );
  }
}

export default App;*/