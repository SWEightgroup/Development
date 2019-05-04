import React, { Component } from 'react';
import { connect } from 'react-redux';
import _translator from '../../../helpers/Translator';
import { downlaodAll, updateFilter } from '../../../actions/AdminActions';

/*
private List<String> languages = new ArrayList<>();
private Long startDate;
private Long endDate;
private Integer minReliability; */
class DeveloperDashboard extends Component {
  constructor(props) {
    super(props);
    this.filterButton = React.createRef();
  }

  handleChange = e => {
    const { updateFilterDispatch } = this.props;
    updateFilterDispatch({ [e.target.id]: e.target.value });
  };

  handleChangeSwitch = e => {
    const { updateFilterDispatch } = this.props;
    updateFilterDispatch({ [e.target.id]: e.target.checked });
  };

  render() {
    const { user, downlaodAllDispatch, filter } = this.props;

    const { language } = user;

    return (
      <React.Fragment>
        <div className="row justify-content-center">
          <div className="py-5 text-center">
            <h2>{_translator('gen_devDashboard', language)}</h2>
            <p className="lead">
              {_translator('developerDashBoard_desc', language)}
            </p>
          </div>
        </div>
        <div className="row justify-content-center">
          <div className="col-md-11">
            <div className="card">
              <h5 className="card-header">
                {_translator('gen_devDashboard', language)}
              </h5>
              <div className="card-body">
                <p>{_translator('developerDashBoard_devDownText', language)}</p>

                <form>
                  <div className="form-group">
                    <div className="custom-control custom-switch">
                      <input
                        type="checkbox"
                        className="custom-control-input"
                        id="openFilters"
                        data-toggle="collapse"
                        data-target="#collapse"
                        aria-controls="collapse"
                        onChange={this.handleChangeSwitch}
                      />
                      <label
                        className="custom-control-label"
                        htmlFor="openFilters"
                      >
                        {_translator('developerDashBoard_addFilter', language)}
                      </label>
                    </div>
                  </div>
                </form>

                <div className="row">
                  <div className="col">
                    <div className="collapse multi-collapse" id="collapse">
                      <form action="">
                        <div className="form-group">
                          <div className="input-group">
                            <div className="input-group-prepend">
                              <span className="input-group-text">
                                {_translator(
                                  'developerDashBoard_minMaxDate',
                                  language
                                )}
                              </span>
                            </div>

                            <input
                              type="date"
                              aria-label="First name"
                              className="form-control"
                              id="dateMin"
                              value={filter.dateMin}
                              onChange={this.handleChange}
                              pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"
                            />
                            <input
                              type="date"
                              aria-label="Last name"
                              className="form-control"
                              id="dateMax"
                              value={filter.dateMax}
                              onChange={this.handleChange}
                              pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"
                            />
                          </div>
                        </div>
                        <div className="form-group">
                          <label htmlFor="reliability">
                            {_translator(
                              'developerDashBoard_minReliability',
                              language
                            )}{' '}
                            : {filter.reliability}
                          </label>
                          <input
                            type="range"
                            className="form-control-range"
                            id="reliability"
                            min="0"
                            max="1"
                            step="0.01"
                            onChange={this.handleChange}
                            value={filter.reliability}
                          />
                        </div>
                      </form>
                    </div>
                  </div>
                </div>
                <button
                  type="button"
                  onClick={() => downlaodAllDispatch()}
                  className="btn btn-primary"
                >
                  {_translator('developerDashBoard_devDown', language)}
                </button>
              </div>
            </div>
          </div>
        </div>
      </React.Fragment>
    );
  }
}

const mapStateToProps = store => {
  return {
    user: store.auth.user,
    filter: store.admin.filter
  };
};

const mapDispatchToProps = dispatch => {
  return {
    downlaodAllDispatch: () => dispatch(downlaodAll()),
    updateFilterDispatch: filter => dispatch(updateFilter(filter))
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DeveloperDashboard);
