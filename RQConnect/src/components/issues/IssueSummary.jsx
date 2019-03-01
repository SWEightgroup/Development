import React, { Component } from 'react';
import moment from 'moment';
import 'moment/locale/it';
import { Link } from 'react-router-dom';
import { updateUC } from '../../store/actions/IssueActions';

import { connect } from 'react-redux';
import { firestoreConnect, isLoaded } from 'react-redux-firebase';
import { compose } from 'redux';
import DoneIssue from './DoneIssue';
import IssueDetails from './IssueDetails';
import { PulseLoader } from 'react-spinners';
import M from 'materialize-css';

class IssueSummary extends Component {
  state = { lista: [], show: false };

  componentDidMount() {
    if (document.getElementById(this.props.issueId)) M.FormSelect.init(document.getElementById(this.props.issueId).querySelectorAll('select'));
  }

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
    const id = { id: this.props.issueId };
    e.preventDefault();
    this.props.updateUC({ ...this.props.thisUsecase, ...this.state, ...id });
    //this.props.history && this.props.history.push("/");
  };

  ciao = () => {
    M.FormSelect.init(document.getElementById(this.props.issueId).querySelectorAll('select'));
    this.setState({ show: true });
  };
  render() {
    const { issueId, usecase, thisUsecase, usersObj } = this.props;
    moment.locale('it');

    const button = (
      <button type="submit" className="btn green white-text ">
        Salva
      </button>
    );

    if (isLoaded(thisUsecase)) {
      const lista = usecase && usecase.filter(item => item.tipo !== thisUsecase.tipo);
      const done = issueId + 'Done';
      const details = issueId + 'Details';

      const elenco = thisUsecase.lista.length ? (
        <ul className="collection">
          {thisUsecase.lista &&
            thisUsecase.lista.map((item, index) => {
              return (
                <li key={item + index + issueId} className="collection-item">
                  {usecase.find(l => l.id === item) && usecase.find(l => l.id === item).title}
                </li>
              );
            })}
        </ul>
      ) : null;

      return (
        <div id={issueId} className="card sticky-action z-depth-0 mt-0">
          <span to={'/issue/' + issueId} className="block card-content grey-text text-darken-3 pt-2 pb-0">
            <span className="grey-text">{thisUsecase.tipo}</span>
            <span className="card-title truncate uppercase mb-0">{thisUsecase.title}</span>
            <span className="grey-text lighten-2">{thisUsecase.fonte}</span> <br />
            <span className="grey-text lighten-1">{thisUsecase.content}</span>
            <span>{elenco}</span>
          </span>
          {/* <DoneIssue issue={thisUsecase} lista={lista} id={done} idIssue={issueId} tipo={thisUsecase.tipo} />
            /*<IssueDetails thisUsecase={thisUsecase} id={details} user={usersObj.find(user => user.id === thisUsecase.authorId)} /> */}
          <div className="card-action p-0 ">
            <form className="white m-0" onSubmit={this.handleSubmit}>
              <div className="col s12" className="z-index-999">
                <label>Tipo di elemento</label>
                <select multiple className="" onChange={this.handleChange}>
                  {lista &&
                    lista.map(element => {
                      return (
                        <option key={element.id} value={element.id}>
                          {element.title}
                        </option>
                      );
                    })}
                </select>
              </div>

              <div className="input-field">
                {this.state.show === true && button}
                {this.state.show === false && (
                  <button type="button" className="btn red white-text  " onClick={this.ciao}>
                    <i className="material-icons">autorenew</i>
                  </button>
                )}
              </div>
            </form>

            {/*<button data-target={done} className="btn green white-text modal-trigger">
              Risolvi
            </button><button data-target={details} className="btn yellow white-text modal-trigger">
              Dettagli
      </button>*/}
          </div>
        </div>
      );
    } else {
      return (
        <div className="card sticky-action z-depth-0 mt-0">
          <PulseLoader sizeUnit={'px'} size={15} color={'#123abc'} loading={true} />
        </div>
      );
    }
  }
}
const mapStateToProps = (state, props) => {
  return {
    auth: state.firebase.auth.id,
    thisUsecase: state.firestore.data[props.issueId],
    usersObj: state.firestore.ordered.users
  };
};
const mapDispatchToProps = dispatch => {
  return {
    updateUC: issue => dispatch(updateUC(issue))
  };
};
export default /*connect(mapStateToProps)(IssueSummary);*/

compose(
  connect(
    mapStateToProps,
    mapDispatchToProps
  ),
  firestoreConnect(props => [{ collection: 'usecase', storeAs: props.issueId, doc: props.issueId }])
)(IssueSummary);
