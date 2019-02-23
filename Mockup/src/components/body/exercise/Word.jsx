import React, { Component } from "react";

class Words extends Component {
  constructor(props) {
    super(props);
    this.state = {
      pulsanti: [],
      indice: -1,
      livelloZero: [],
      solution: ""
    };
  }

  /**
   * Genero la prima lista dei pulsanti in base all'elemento selezionato
   *
   * @param {*} item Elemento selezionato(livello 0)
   */
  generaLivelloUno(item) {
    const livello = Object.values(item.data)[0];
    if (livello) this.setState({ indice: 0, pulsanti: livello, livelloZero: item, solution: item.text });
  }

  /**
   * Genero la lista di pulsanti successiva
   *
   * @param {*} item  Elemento selezionato(secondo livello)
   */
  generaSuccessivo(item) {
    const indice = this.state.indice + 1;
    console.log("item " + indice + " : ", item);
    const pulsanti = Object.values(this.state.livelloZero.data)[indice];
    const solution = this.state.solution + " " + item[0];
    if (pulsanti) {
      this.setState({ indice, pulsanti, solution });
    } else this.setState({ pulsanti: [], solution });
  }

  render() {
    const { parola, ger } = this.props;
    const { pulsanti } = this.state;
    const livello1 = Object.values(ger);
    return (
      <li className="list-group-item">
        <p>
          <span>
            <button className="border-0 btn btn-outline-danger btn-sm">
              <i className="material-icons">settings_backup_restore</i>
            </button>{" "}
            {parola}
          </span>
          {" : "}
          <span>{this.state.solution}</span>
        </p>

        {/* questa parte viene eseguita solo per gli elementi di primo livello (agettivo, pronome, nome, ecc..) */}
        {this.state.indice === -1 &&
          livello1.map((item, index) => {
            return (
              <button className="btn btn-outline-primary m-1" key={"index" + index} onClick={() => this.generaLivelloUno(item, 0)}>
                {item.text}
              </button>
            );
          })}

        {/**
          questa parte viene eseguita solo per gli elementi di secondo livello
          */}
        {pulsanti.length > 0 &&
          pulsanti.map((item, index) => {
            return (
              <button
                className="btn btn-outline-primary m-1"
                key={"index" + index}
                onClick={() => this.generaSuccessivo(item, this.state.indice + 1)}
              >
                {item[0]}
              </button>
            );
          })}
      </li>
    );
  }
}
export default Words;
