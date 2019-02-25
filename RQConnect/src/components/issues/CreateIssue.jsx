import React, { Component } from "react";
import { connect } from "react-redux";
import { createUC } from "../../store/actions/IssueActions";
import { Redirect } from "react-router-dom";
import { compose } from "redux";
import { firestoreConnect } from "react-redux-firebase";
import M from "materialize-css/dist/js/materialize";
import { PulseLoader } from "react-spinners";
class CreateIssue extends Component {
  state = {
    title: "",
    content: "",
    tipo: "",
    lista: [],
    fonte: ""
  };

  loading = false;
  componentDidMount() {
    var elems = document.querySelectorAll("select");
    M.FormSelect.init(elems);
  }
  componentDidUpdate() {
    var elems = document.querySelectorAll("select");
    M.FormSelect.init(elems);
  }
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
    this.loading = true;
    this.props.createUC({ ...this.state });
    //this.props.history.push("/");
    this.setState({ title: "", content: "", tipo: "", lista: [], fonte: "" });
    document.getElementById("title").value = "";
    document.getElementById("content").value = "";
    document.getElementById("tipo").value = "";
    this.loading = false;
  };
  render() {
    const { auth } = this.props;
    if (!auth.uid) return <Redirect to="/signin" />;
    return (
      <div className="container">
        <form className="white" onSubmit={this.handleSubmit}>
          <h5 className="grey-text text-darken-3">Aggiungi</h5>
          <div className="input-field">
            <input type="text" id="title" onChange={this.handleChange} required className="validate" autoFocus />
            <label htmlFor="title">Titolo</label>

            <span className="error red-text" />
          </div>

          <div className="input-field">
            <textarea id="content" className="materialize-textarea validate" onChange={this.handleChange} />
            <label htmlFor="content">Contenuto</label>
          </div>

          <div className=" col s12">
            <label>Tipo di elemento</label>
            <select id="tipo" name="tipo" onChange={this.handleChange} className="validate browser-default" defaultValue="" required>
              <option value="">Seleziona un elemento</option>
              <option value="UC">Caso d'uso</option>
              <option value="RQ">Requisito</option>
            </select>

            <span className="error red-text" />
          </div>
          <div className=" col s12">
            <label>Fonte</label>
            <select id="fonte" name="fonte" onChange={this.handleChange} className="validate browser-default" defaultValue="">
              <option value="">Seleziona un elemento</option>
              <option value="Capitolato">Capitolato</option>
              <option value="Interno">Interno</option>
            </select>

            <span className="error red-text" />
          </div>

          <div className="input-field">
            <button type="submit" className="btn pink lighten-1">
              Aggiungi
            </button>
            <PulseLoader sizeUnit={"px"} size={10} color={"#123abc"} loading={this.loading} />
          </div>
        </form>
      </div>
    );
  }
}

const mapStateToProps = state => {
  return {
    users: state.firestore.ordered.users,
    auth: state.firebase.auth
  };
};

const mapDispatchToProps = dispatch => {
  return {
    createUC: issue => dispatch(createUC(issue))
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CreateIssue);
/* compose(
  connect(
    mapStateToProps,
    mapDispatchToProps
  ),
  firestoreConnect([{ collection: "users", orderBy: ["firstName", "asc"] }])
)(CreateIssue);*/
