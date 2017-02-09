import React, { Component, PropTypes } from 'react';
import TypeList from '../TypeManager/TypeList.jsx'

export default class HomePage extends Component {

    // This seems to be a way to validate this component receives some props.
    /*    static propTypes = {
     // This React component receives the login function to be dispatched as a prop,
     // so it doesnt have to know about the implementation.
     loginAction: PropTypes.func.isRequired
     };
     */
    constructor() {
        super();
        console.log('constructor');
        this.state = {
            errorMessage: '',
            isLoadingList: false
        };
    }

    componentDidMount() {
        debugger;
        console.log(this.props);
    }

    render() {
        /*
         const { options, value } = this.state;
         const selectedCount = value.length;
         const availableCount = options.length - selectedCount;
         */
        this.__ = key => this.props.startUp.translations[key];

        console.log('render');

        return (
            <div >
                <div>
                    {this.__('amp.resource-manager:resource-manager-title')}
                </div>
                <div>
                    <TypeList/>
                </div>
            </div>
        );
    }
}

//http://stackoverflow.com/questions/33455166/how-to-set-up-babel-6-stage-0-with-react-and-webpack
