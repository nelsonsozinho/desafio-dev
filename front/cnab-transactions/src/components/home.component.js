import Paper from '@mui/material/Paper';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import TextField from '@mui/material/TextField';
import React, { Component } from "react";
import Form from "react-validation/build/form";
import Button from '@mui/material/Button';
import ButtonGroup from '@mui/material/ButtonGroup';


import { withRouter } from '../common/with-router';


import UserService from "../services/user.service";

class Home extends Component {

  constructor(props) {
    super(props);
    this.onChangePropertyNameFilter = this.onChangePropertyNameFilter.bind(this);
    this.handleTransaction = this.handleTransaction.bind(this);
    this.handleFileChange = this.handleFileChange.bind(this);
    this.handleFileUpload = this.handleFileUpload.bind(this);
    this.state = {
      content: "",
      propertyNameFilter: "",
      file: ""
    };

  }

  componentDidMount() {
    UserService.getAllTransactions().then(
      response => {
        this.setState({
          content: response.data
        });
      },
      error => {
        this.setState({
          content:
            (error.response && error.response.data) ||
            error.message ||
            error.toString()
        });
      }
    );
  }

  onChangePropertyNameFilter(e) {
    this.setState(
      {
        propertyNameFilter: e.target.value
      }
    );
  }

  handleFileChange(e) {
      this.setState({file: e.target.files[0]})
  }


  handleFileUpload(e) {
    UserService.fileUpload(this.state.file).then(
      response => {
        this.setState({
          content: response
        });
      },
      error => {
        this.setState({
          content:
            (error.response && error.response.data) ||
            error.message ||
            error.toString()
        });
      }
    );
  }


  handleTransaction(e) {
    e.preventDefault();
    UserService.filterTransactions(this.state.propertyNameFilter).then(
      response => {
        this.setState({
          content: response.data
        });
      },
      error => {
        this.setState({
          content:
            (error.response && error.response.data) ||
            error.message ||
            error.toString()
        });
      }
    );
  }

  formatValue(value) {
    return new Intl.NumberFormat('pt-BR', {
      style: 'currency',
      currency: 'BRL'
    }).format(value);
  }

  render() {
    return (
      <div className="container">
        <header className="jumbotron">
          <h3>Transactions</h3>
        </header>

        {this.state.content.transactions?.length === 0 && (
          <div>
            <ButtonGroup
              disableElevation
              variant="contained"
              aria-label="Disabled elevation buttons"
            >
              <TextField  type="file" onChange={this.handleFileChange} />
              <Button size="large" variant="contained" onClick={this.handleFileUpload}>Upload</Button>
            </ButtonGroup>
            
          </div>
        )}

        <Form
          onSubmit={this.handleTransaction}
          ref={c => {
            this.form = c;
          }}
        >
          {this.state.content.transactions?.length > 0 && (
            <div className="form-group">
              <TextField
                value={this.state.propertyNameFilter}
                onChange={this.onChangePropertyNameFilter}
                fullWidth
                label="Property Name"
                id="propertyNameFilter"
              />
            </div>
          )}
        </Form>

        {this.state.content.transactions?.length && (
        <div>
          <div>
            <caption>Total</caption>
            {this.formatValue(this.state.content.transactionAmount)}
          </div>
          <TableContainer component={Paper}>
            <Table sx={{ minWidth: 650 }} aria-label="simple table">
              <TableHead>
                <TableRow>
                  <TableCell>Owner Name</TableCell>
                  <TableCell align="right">Property Name</TableCell>
                  <TableCell align="right">Value</TableCell>
                  <TableCell align="right">Date</TableCell>
                  <TableCell align="right">T. Type</TableCell>
                </TableRow>
              </TableHead>

              <TableBody>
                {this.state.content.transactions && this.state.content.transactions.map((content) => (
                  <TableRow
                    key={content.id}
                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                  >
                    <TableCell component="th" scope="row">
                      {content.ownerName}
                    </TableCell>
                    <TableCell align="right">{content.storeName}</TableCell>
                    <TableCell align="right">{content.value}</TableCell>
                    <TableCell align="right">{content.data}</TableCell>
                    <TableCell align="right">{content.nature}</TableCell>
                  </TableRow>
                ))}
              </TableBody>

            </Table>
          </TableContainer>
          
        </div>
        )}

      </div>
    );
  }
}

export default withRouter(Home);