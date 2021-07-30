import React, { Component } from 'react';
import { Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { withRouter } from 'react-router-dom';

class EntryList extends Component {

    constructor(props) {
        super(props);
        this.state = {entries: []};
    }

    componentDidMount() {
        fetch('/entry')
            .then(response => response.json())
            .then(data => this.setState({entries: data}));
    }
    
    render() {
        const {entries, isLoading} = this.state;
    
        if (isLoading) {
            return <p>Loading...</p>;
        }
    
        const entryList = entries.map(entry => {
            return <tr key={entry.id}>
                <td style={{whiteSpace: 'nowrap'}}>{entry.deviceType}</td>
                <td>{entry.deviceOwner}</td>
                <td>{entry.deviceBarcode}</td>
                <td>{entry.roomNumber}</td>
            </tr>
        });
    
        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <h3>Device Entries</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="25%">Device Type</th>
                            <th width="25%">Device Owner</th>
                            <th width="25%">Device Barcode</th>
                            <th width="25%">Room Number</th>
                        </tr>
                        </thead>
                        <tbody>
                        {entryList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}
export default withRouter(EntryList);