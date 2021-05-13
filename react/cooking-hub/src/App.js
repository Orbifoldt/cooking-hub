import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
// import Recipe from './Recipe';
import Recipe from './recipes/Recipe';
import Header from './common/Header';

// function App() {
//   return (
//     <div className="App">
//       <header className="App-header">
//         <img src={logo} className="App-logo" alt="logo" />
//         <p>
//           Edit <code>src/App.js</code> and save to reload!!!.
//         </p>
//         <a
//           className="App-link"
//           href="https://reactjs.org"
//           target="_blank"
//           rel="noopener noreferrer"
//         >
//           Learn React
//         </a>
//       </header>
//     </div>
//   );
// }

class App extends Component {
  state = {
    isLoading: true,
    groups: []
  };

  async componentDidMount() {
    const response = await fetch('/api/yep?id=2');
    const body = await response.json();
    this.setState({ groups: body, isLoading: false });
  }

  render() {
    const {groups, isLoading} = this.state;

    // if (isLoading) {
    //   return <p>Loading...</p>;
    // }

    console.log(groups)

    // for(let x in groups){
    //   console.log(x)
    // }


    return (
      <div className="App">
        {/* <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <div className="App-intro"> */}
            {/* <div key="yeppers"> {groups.recipe}</div> */}
            
          {/* </div>
        </header> */}
        <Header />
        <Recipe />
      </div>
    );
  }
}

export default App;
