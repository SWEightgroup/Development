import React, { Component } from 'react';
import _translator from '../../helpers/Translator';

class InputSentence extends Component {
  handleChange = e => {
    e.preventDefault();
    this.props.changeNewInputSentence(e.target.value);
  };

  removePunctuation = sentenceString => {
    const sentence = sentenceString;
    const punctuationRegEx = /[--@[-`{-~¡-©«-¬®-±´¶-»¿×÷˂-˅˒-˟˥-˫˭˯-˿͵΄-΅·϶҂՚-՟-֊־׀׃׆׳-״؆-؏؛؞-؟٪-٭۔۩۽-۾܀-܍߶-߹।-॥॰৲-৳৺૱୰௳-௺౿ೱ-ೲ൹෴฿๏๚-๛༁-༗༚-༟༴༶༸༺-༽྅྾-࿅࿇-࿌࿎-࿔၊-၏႞-႟჻፠-፨᎐-᎙᙭-᙮᚛-᚜᛫-᛭᜵-᜶។-៖៘-៛᠀-᠊᥀᥄-᥅᧞-᧿᨞-᨟᭚-᭪᭴-᭼᰻-᰿᱾-᱿᾽᾿-῁῍-῏῝-῟῭-`´-῾\u2000-\u206e⁺-⁾₊-₎₠-₵℀-℁℃-℆℈-℉℔№-℘℞-℣℥℧℩℮℺-℻⅀-⅄⅊-⅍⅏←-⏧␀-␦⑀-⑊⒜-ⓩ─-⚝⚠-⚼⛀-⛃✁-✄✆-✉✌-✧✩-❋❍❏-❒❖❘-❞❡-❵➔➘-➯➱-➾⟀-⟊⟌⟐-⭌⭐-⭔⳥-⳪⳹-⳼⳾-⳿⸀-\u2e7e⺀-⺙⺛-⻳⼀-⿕⿰-⿻\u3000-〿゛-゜゠・㆐-㆑㆖-㆟㇀-㇣㈀-㈞㈪-㉃㉐㉠-㉿㊊-㊰㋀-㋾㌀-㏿䷀-䷿꒐-꓆꘍-꘏꙳꙾꜀-꜖꜠-꜡꞉-꞊꠨-꠫꡴-꡷꣎-꣏꤮-꤯꥟꩜-꩟﬩﴾-﴿﷼-﷽︐-︙︰-﹒﹔-﹦﹨-﹫！-／：-＠［-｀｛-･￠-￦￨-￮￼-�]]/g;
    const allowedPunctuation = /[,.?!"'<->;:]/g;
    const cleanString = sentence
      .replace(allowedPunctuation, x => ` ${x} `)
      .replace(punctuationRegEx, '')
      .replace(/(\s){2,}/g, '$1')
      .trim();
    return cleanString;
  };

  handleSubmit = e => {
    e.preventDefault();
    const { sentenceString } = this.props;
    const cleanString = this.removePunctuation(sentenceString);

    document.getElementById('sentenceString').value = cleanString;
    const { prepareExercise } = this.props;
    prepareExercise(cleanString);
  };

  render() {
    const { language } = this.props;
    return (
      <div className="row justify-content-center">
        <div className="col-12">
          <div className="main-card mb-3 card">
            <div className="card-body">
              <h5 className="card-title ">
                {_translator('inputSentence_insertSentence', language)}
              </h5>

              <form
                onSubmit={this.handleSubmit}
                className="needs-validation was-validated"
              >
                <div className="input-group">
                  <input
                    id="sentenceString"
                    type="text"
                    className="form-control validate"
                    placeholder={_translator(
                      'inputSentence_insertSentence',
                      language
                    )}
                    onChange={this.handleChange}
                    required
                  />
                  <div className="input-group-append">
                    <button className="btn btn-success" type="submit">
                      {_translator('inputSentence_executeExercise', language)}
                    </button>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default InputSentence;
