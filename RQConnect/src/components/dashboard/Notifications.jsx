import React from "react";
import moment from "moment";
const Notifications = props => {
  const { notifications, users } = props;
  return (
    <div className="section section-fixed .scroll white-01 p-2">
      <div className="card z-depth-0 m-0">
        <div className="card-content px-0">
          <span className="card-title pl-3">Notifiche</span>
          <ul className="online-users scroll collection">
            {notifications &&
              notifications.map(item => {
                let src = "https://www.wired.it/wp-content/plugins/wordpress-social-login/assets/images/default-avatar.jpg";
                let alt = "foto profilo";
                let authorName = "";
                if (users && users[item.userAuthor]) {
                  authorName = users[item.userAuthor].firstName + " " + users[item.userAuthor].lastName;
                  src = users[item.userAuthor].linkPhoto;
                }
                if (users && users[item.userSelectedId]) {
                }
                return (
                  <li key={item.id} className="collection-item avatar">
                    <img src={src} alt={alt + " di " + authorName} className="circle" />
                    <span className="title blue-text">{authorName}</span>
                    <p>{item.content}</p>

                    <div className="note-date grey-text">{moment(item.time.toDate()).fromNow()}</div>
                  </li>
                );
              })}
          </ul>
        </div>
      </div>
    </div>
  );
};

export default Notifications;
