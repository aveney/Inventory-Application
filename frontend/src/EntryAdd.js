import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';

class EntryAdd extends Component {

    emptyItem = {
        id: ''
    };

    emptyItem1 = {
        deviceType: '',
        deviceOwner: '',
        deviceBarcode: '',
        roomNumber:''
    }

    constructor(props) {
        super(props);
        this.state = {
            thisUser: this.emptyItem,
            thisEntry: this.emptyItem1
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        const user = await (await fetch(`/user/${this.props.match.params.id}`)).json();
        this.setState({thisUser: user});
        this.setState({thisEntry: this.state.thisEntry});
    }
    

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let thisEntry = {...this.state.thisEntry};
        thisEntry[name] = value;
        this.setState({thisEntry});
    }

    async handleSubmit(event) {
        event.preventDefault();
        //const {thisUser} = this.state.thisUser;
        //const {thisEntry} = this.state.thisEntry;
    
        await fetch('/entry/' + this.state.thisUser.id, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(this.state.thisEntry),
        });
        this.props.history.push('/entry/' + this.state.thisUser.id);
        alert("Entry Successful!")
    }

    render() {
        //const {thisUser} = this.state.thisUser;
        //const {thisEntry} = this.state.thisEntry;
        const title = <h2>{'Add Entry'}</h2>;
    
        return <div>
            <AppNavbar/>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="deviceType">Device Type</Label>
                        <Input type="text" name="deviceType" id="deviceType" value={this.state.thisEntry.deviceType || ''}
                               onChange={this.handleChange} autoComplete="deviceType"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="deviceOwner">Device Owner</Label>
                        <Input type="text" name="deviceOwner" id="deviceOwner" value={this.state.thisEntry.deviceOwner || ''}
                               onChange={this.handleChange} autoComplete="deviceOwner"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="deviceBarcode">Device Barcode</Label>
                        <Input type="text" name="deviceBarcode" id="deviceBarcode" value={this.state.thisEntry.deviceBarcode || ''}
                               onChange={this.handleChange} autoComplete="deviceBarcode"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="roomNumber">Room Number</Label>
                        <Input type="text" name="roomNumber" id="roomNumber" value={this.state.thisEntry.roomNumber || ''}
                               onChange={this.handleChange} autoComplete="roomNumber"/>
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/user">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}
export default withRouter(EntryAdd);