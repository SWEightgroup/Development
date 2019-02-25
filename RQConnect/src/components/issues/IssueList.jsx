import React, { Component } from "react";
import IssueSummary from "./IssueSummary";

class IssueList extends Component {
  render() {
    const { issues, users, title, usecase } = this.props;

    if (issues) {
      const i = "\\begin{table}      \\centering      \\begin{tabular}{ll}";
      const p = "\\end{tabular} \\end{table}";
      let exp = "";

      /**
       * Salvo nella variabile exp tutta la tabella da esportare
       */
      this.props.issues.map((item, index, array) => {
        const pref = "\\begin{tabular}[c]{@{}l@{}}";
        const post = "\\end{tabular}";
        let collegati = "";
        item.lista &&
          item.lista.forEach((item2, index2, array2) => {
            let toReturn = this.props.usecase.find(item3 => item3.id === item2) && this.props.usecase.find(item3 => item3.id === item2).title;
            if (index2 < array2.length - 1) toReturn += " \\\\";
            collegati += toReturn;
          });
        let toReturnP = "" + item.title + " & " + pref + collegati + post;
        if (index < array.length - 1) toReturnP += " \\\\";
        exp += toReturnP;
        return null;
      });

      const json = "text;charset=utf-8," + encodeURIComponent(i + exp + p);
      return (
        <div>
          <div className="white lighten-2 py-2 px-3 mt-2">
            {title}

            <a className="green  white-text right px-2" href={`data: ${json}`} download="data1.json">
              Download JSON
            </a>
          </div>
          <div className=" section section-fixed no-scrollbar mt-0 p-2 pb-0 white-01">
            {issues &&
              issues.map((issue, index) => {
                return <IssueSummary usecase={usecase} issueId={issue.id} key={issue.id} users={users} />;
              })}
          </div>
        </div>
      );
    } else return <div />;
  }
}

export default IssueList;
