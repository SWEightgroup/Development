import React, { Component } from 'react';
import { connect } from 'react-redux';
import _translator from '../../../helpers/Translator';

import {
  getFavouriteTeachers,
  getAllTeachers,
  removeFavouriteTeachers,
  addFavouriteTeachers
} from '../../../actions/ClassManagementActions';
import Pagination from '../../components/Paginatioin';

class FavouriteTeachers extends Component {
  state = {};

  constructor(props) {
    super(props);
    props.getAllTeachersDispatch();
    props.getFavouriteTeachersDispatch();
  }

  render() {
    const {
      auth,
      teachersList,
      favouriteTeachers,
      getAllTeachersDispatch,
      addFavouriteTeachersDispatch,
      removeFavouriteTeachersDispatch
    } = this.props;
    const { user } = auth;

    const { language } = user;
    return (
      <React.Fragment>
        <div className="row justify-content-center">
          <div className="col-sm-6">
            <div className="main-card mb-3 card">
              <div className="card-header">
                <i className="header-icon lnr-laptop-phone icon-gradient bg-plum-plate" />
                {_translator('favouriteTeachers_allTeacher', language)}
              </div>
              <div className="card-body">
                <div className="scroll-area-xxl">
                  <div className="scrollbar-container ps--active-y ps">
                    <ul className="list-group list-group-flush">
                      {teachersList &&
                        teachersList.teachers &&
                        teachersList.teachers.map(teacher => {
                          let indexTeacherFavourite = null;
                          indexTeacherFavourite = favouriteTeachers.findIndex(
                            _teacher => _teacher.id === teacher.id
                          );

                          return (
                            <li className="list-group-item " key={teacher.id}>
                              {teacher.firstName} {teacher.lastName}
                              {indexTeacherFavourite !== null &&
                                indexTeacherFavourite === -1 && (
                                  <button
                                    type="button"
                                    className="btn btn-sm btn-success  pull-right"
                                    onClick={() =>
                                      addFavouriteTeachersDispatch(teacher)
                                    }
                                  >
                                    <i className="fa fa-plus" />
                                  </button>
                                )}
                            </li>
                          );
                        })}
                    </ul>
                    <div className="ps__rail-x">
                      <div className="ps__thumb-x" />
                    </div>
                    <div className="ps__rail-y">
                      <div className="ps__thumb-y" />
                    </div>
                  </div>
                  {teachersList && (
                    <Pagination
                      first={teachersList.links.first}
                      last={teachersList.links.last}
                      next={teachersList.links.next}
                      prev={teachersList.links.prev}
                      number={teachersList.page.number}
                      totalPages={teachersList.page.totalPages}
                      language={auth.user.language}
                      onClick={getAllTeachersDispatch}
                    />
                  )}
                </div>
              </div>
            </div>
          </div>
          <div className="col-sm-6">
            <div className="main-card mb-3 card">
              <div className="card-header">
                <i className="header-icon lnr-laptop-phone icon-gradient bg-plum-plate" />
                {_translator(
                  'sidebarElementStudent_favoriteTeachers',
                  language
                )}
              </div>
              <div className="card-body">
                <div className="scroll-area-xxl">
                  <div className="scrollbar-container ps--active-y ps">
                    <ul className="list-group list-group-flush">
                      {favouriteTeachers &&
                        favouriteTeachers.map(teacher => (
                          <li
                            className="list-group-item hover"
                            key={teacher.id}
                          >
                            {teacher.firstName} {teacher.lastName}
                            <button
                              type="button"
                              className="btn btn-sm btn-danger btn-hover pull-right"
                              onClick={() =>
                                removeFavouriteTeachersDispatch(teacher.id)
                              }
                            >
                              <i className="fa fa-minus" />
                            </button>
                          </li>
                        ))}
                    </ul>
                    <div className="ps__rail-x">
                      <div className="ps__thumb-x" />
                    </div>
                    <div className="ps__rail-y">
                      <div className="ps__thumb-y" />
                    </div>
                  </div>
                </div>
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
    authError: store.auth.authError,
    auth: store.auth,
    teachersList: store.class.teachersList,
    favouriteTeachers: store.class.favouriteTeachers
  };
};

const mapDispatchToProps = dispatch => {
  return {
    getFavouriteTeachersDispatch: () => dispatch(getFavouriteTeachers()),
    getAllTeachersDispatch: link => dispatch(getAllTeachers(link)),
    removeFavouriteTeachersDispatch: teacherId =>
      dispatch(removeFavouriteTeachers(teacherId)),
    addFavouriteTeachersDispatch: teacher =>
      dispatch(addFavouriteTeachers(teacher))
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FavouriteTeachers);
