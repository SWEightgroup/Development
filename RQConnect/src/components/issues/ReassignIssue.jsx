import React, { Component } from "react";
import { connect } from "react-redux";
import { reassignIssue } from "../../store/actions/IssueActions";
import M from "materialize-css/dist/js/materialize";
class ReassignIssue extends Component {
  state = {
    motivazione: "",
    userSelectedId: ""
  };
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
  };
  handleSubmit = e => {
    e.preventDefault();
    const user = this.props.users[this.state.userSelectedId];
    const userSelectedName = user.firstName + " " + user.lastName;
    this.setState({ userSelectedName });
    this.props.reassignIssue({ ...this.props.issue, ...this.state });
  };
  render() {
    let users = [];
    if (this.props.users) {
      var usrs = this.props.users;
      users = Object.keys(usrs).map(key => {
        return { ...usrs[key], id: key };
      });
    }
    return (
      <div id={this.props.id} className="modal">
        <div className="modal-content">
          <h4>Riassegna as un'altro utente</h4>
          <form className="white" onSubmit={this.handleSubmit}>
            <div className="input-field">
              <textarea id="motivazione" className="materialize-textarea" onChange={this.handleChange} required />
              <label htmlFor="motivazione">Motivazione</label>
            </div>
            <div className="input-field ">
              <select required id="userSelectedId" onChange={this.handleChange} required>
                <option>Seleziona un utente</option>
                {users &&
                  users.map(user => {
                    return (
                      <option key={user.id} value={user.id}>
                        {user.firstName} {user.lastName}
                      </option>
                    );
                  })}
                }
              </select>
              <label>Utente destinatario</label>
            </div>
            <div className="input-field">
              <button type="submit" className="modal-close btn green white-text ">
                Salva
              </button>
              <button type="submit" className="modal-close btn red white-text  ">
                Annulla
              </button>
            </div>
          </form>
        </div>
      </div>
    );
  }
}

const mapDispatchToProps = dispatch => {
  return {
    reassignIssue: issue => dispatch(reassignIssue(issue))
  };
};

export default connect(
  null,
  mapDispatchToProps
)(ReassignIssue);
