import React, { Component } from "react";
import IssueList from "../issues/IssueList";
import { connect } from "react-redux";
import { firestoreConnect } from "react-redux-firebase";
import { compose } from "redux";
import { Redirect } from "react-router-dom";
class Dashboard extends Component {
  state = {
    frase: "",
    risultato: []
  };

  handleChange = e => {
    this.setState({
      [e.target.id]: e.target.value
    });

    let error = e.target.parentNode.querySelector(".error");
    if (error && e.target.value === "") {
      error.innerHTML = "Campo obbligatorio";
    } else if (error) {
      error.innerHTML = "";
    }
  };
  handleSubmit = e => {
    e.preventDefault();
    fetch("http://localhost:8081/grammatical-analysis/" + this.state.frase)
      .then(res => res.json())
      .then(res => {
        this.setState({ risultato: res });
        console.log(res);
      })
      .catch(console.log("error"));
  };

  render() {
    const { usecase, auth, users } = this.props;
    if (!auth.uid) return <Redirect to="/signin" />;
    if (usecase) {
      const requisiti = usecase.filter(item => item.tipo === "RQ");
      const us = usecase.filter(item => item.tipo === "UC");

      let table = this.state.risultato.length ? (
        <div className="row">
          <div className="col s12 m6 white mt-2 lighten-1">
            <table>
              <caption>Solution</caption>
              <thead>
                <tr>
                  <th>1</th>
                  <th>2</th>
                  <th>3</th>
                  <th>4</th>
                </tr>
              </thead>
              <tbody>
                {this.state.risultato &&
                  this.state.risultato.map((item, index) => {
                    return (
                      <tr key={index}>
                        <td>{item[0]}</td>
                        <td>{item[1]}</td>
                        <td>{item[2]}</td>
                        <td>{item[3]}</td>
                      </tr>
                    );
                  })}
              </tbody>
            </table>
          </div>
        </div>
      ) : (
        ""
      );

      return (
        <div className="dashboard container ">
          <div className="row">
            {}
            <form className="white" onSubmit={this.handleSubmit}>
              <div className="input-field">
                <input type="text" id="frase" onChange={this.handleChange} required className="validate" autoFocus />
                <label htmlFor="title">Frase</label>

                <span className="error red-text" />
              </div>
              <div className="input-field">
                <button type="submit" className="btn pink lighten-1">
                  Test
                </button>
              </div>
            </form>
            {table}
          </div>
          <div className="row">
            <div className="col s12 m6 pb-2 ">
              <IssueList key="Requisiti" usecase={usecase} issues={requisiti} uid={auth.uid} users={users} title="Requisiti" />
            </div>
            <div className="col s12 m6 ">
              <IssueList key="Usecase" usecase={usecase} issues={us} uid={auth.uid} users={users} title="Casi d'uso" />
            </div>
          </div>
        </div>
      );
    } else return "loading";
  }
}

const mapStateToProps = state => {
  return {
    usecase: state.firestore.ordered.usecase,
    auth: state.firebase.auth,
    //notifications: state.firestore.ordered.notifications,
    users: state.firestore.data.users,
    usersObj: state.firestore.ordered.users
  };
};

export default compose(
  connect(mapStateToProps),
  firestoreConnect([
    { collection: "users", orderBy: ["firstName", "asc"] },
    { collection: "usecase", orderBy: ["createdAt", "desc"] }
    /*{ collection: "notifications", limit: 3, orderBy: ["time", "desc"] },*/
  ])
)(Dashboard);

firestoreConnect([]);
