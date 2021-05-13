import React, { Component } from 'react';
import './Header.css';

import logo from './../logo.svg';

class Header extends Component{

    constructor(props){
        super(props);
        console.log("Constructor has been called!");
    } 


    render(){
        return <header className="Header">
            <Logo /> <Navbar /> Hello
            </header>;
    }
}


class Navbar extends Component{

    constructor(props){
        super(props);
        this.state = {};
    }

    render(){
        return <div class="nav-grid">Navbar</div>;
    }
}


class Logo extends Component{

    constructor(props){
        super(props);
        this.state = {};
    }

    render(){
        return <div className="logo"><img src={logo} alt="The logo."/> Logo</div>;
    }
}


export default Header;