import React, { Component } from "react";
import M from "materialize-css";
import { connect } from "react-redux";
import { updateUC } from "../../store/actions/IssueActions";

class DoneIssue extends Component {
  state = { lista: [] };

  handleChange = event => {
    let opts = [],
      opt;

    for (let i = 0, len = event.target.options.length; i < len; i++) {
      opt = event.target.options[i];
      if (opt.selected) {
        opts.push(opt.value);
      }
    }
    this.setState({ lista: opts });
  };

  handleSubmit = e => {
    const id = { id: this.props.idIssue };
    e.preventDefault();
    this.props.updateUC({ ...this.props.issue, ...this.state, ...id });
    //this.props.history && this.props.history.push("/");
  };

  componentDidMount() {
    var elems = document.querySelectorAll(".modal");
    M.Modal.init(elems);
    var select = document.querySelectorAll("select");
    M.FormSelect.init(select);
  }

  render() {
    const { lista } = this.props;

    return (
      //Modal Structure
      <div id={this.props.id} className="modal bottom-sheet h-100  w-800">
        <div className="modal-content">
          <h4>Descrizione delle soluzione</h4>
          <form className="white" onSubmit={this.handleSubmit}>
            <div className="col s12">
              <label>Tipo di elemento</label>
              <select multiple className="" onChange={this.handleChange}>
                {/*lista &&
                  lista.map(element => {
                    return (
                      <option key={element.id} value={element.id}>
                        {element.title}
                      </option>
                    );
                  })*/}
              </select>
            </div>

            <div className="input-field">
              <button type="submit" className="modal-close btn green white-text ">
                Salva
              </button>
              <button href="#!" className="modal-close btn red white-text  ">
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
    updateUC: issue => dispatch(updateUC(issue))
  };
};
export default connect(
  null,
  mapDispatchToProps
)(DoneIssue);
