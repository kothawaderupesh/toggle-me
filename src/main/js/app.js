'use strict';


const React = require('react'); 
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {features: []};
	}

	componentDidMount() {
		client({method: 'GET', path: '/api/feature'}).done(response => {
		    this.setState({features: response.entity._embedded.feature});

		});
	}

	render() {
		return (
			<FeaturesList features={this.state.features}/>
		)
	}
}


class FeaturesList extends React.Component {
    render() {
        const features = this.props.features.map(feature =>
            <Feature key={feature._links.self.href} feature={feature}/>
        );
        return (
            <table>
                <tbody>
                <tr>
                    <th>Name</th>
                    <th>Is Active</th>
                </tr>
                {features}
                </tbody>
            </table>
        )
    }
}


class Feature extends React.Component {
    render() {
        return (
            <tr>
                <td>{this.props.feature.name}</td>
                <td>{this.props.feature.active}</td>
            </tr>
        )
    }
}


ReactDOM.render(
	<App />,
	document.getElementById('react')
)
