import React, { Component } from 'react';

class InputSentence extends Component {
  handleChange = e => {
    e.preventDefault();
    this.props.changeNewInputSentence(e.target.value);
  };

  handleSubmit = e => {
    e.preventDefault();
    let { sentenceString } = this.props;
    // |\ud800[\udd00-\udd02\udd37-\udd79-\udd89\udd90-\udd9b\uddd0-\uddfc\udf9f\udfd0]|\ud802[\udd1f\ude50-\ude58]|\ud809[\udc00-\udc7e]|\ud834[\udc00-\udcf5\udd00-\udd26\udd29-\udd64\udd6a-\udd6c\udd83-\udd84\udd8c-\udda9\uddae-\udddd\ude00-\ude41\ude45\udf00-\udf56]|\ud835[\udec1\udedb\udefb\udf15\udf35\udf4f\udf6f\udf89\udfa9\udfc3]|\ud83c[\udc00-\udc2b\udc30-\udc93
    const punctuationRegEx = /[--@[-`{-~¡-©«-¬®-±´¶-»¿×÷˂-˅˒-˟˥-˫˭˯-˿͵΄-΅·϶҂՚-՟-֊־׀׃׆׳-״؆-؏؛؞-؟٪-٭۔۩۽-۾܀-܍߶-߹।-॥॰৲-৳৺૱୰௳-௺౿ೱ-ೲ൹෴฿๏๚-๛༁-༗༚-༟༴༶༸༺-༽྅྾-࿅࿇-࿌࿎-࿔၊-၏႞-႟჻፠-፨᎐-᎙᙭-᙮᚛-᚜᛫-᛭᜵-᜶។-៖៘-៛᠀-᠊᥀᥄-᥅᧞-᧿᨞-᨟᭚-᭪᭴-᭼᰻-᰿᱾-᱿᾽᾿-῁῍-῏῝-῟῭-`´-῾\u2000-\u206e⁺-⁾₊-₎₠-₵℀-℁℃-℆℈-℉℔№-℘℞-℣℥℧℩℮℺-℻⅀-⅄⅊-⅍⅏←-⏧␀-␦⑀-⑊⒜-ⓩ─-⚝⚠-⚼⛀-⛃✁-✄✆-✉✌-✧✩-❋❍❏-❒❖❘-❞❡-❵➔➘-➯➱-➾⟀-⟊⟌⟐-⭌⭐-⭔⳥-⳪⳹-⳼⳾-⳿⸀-\u2e7e⺀-⺙⺛-⻳⼀-⿕⿰-⿻\u3000-〿゛-゜゠・㆐-㆑㆖-㆟㇀-㇣㈀-㈞㈪-㉃㉐㉠-㉿㊊-㊰㋀-㋾㌀-㏿䷀-䷿꒐-꓆꘍-꘏꙳꙾꜀-꜖꜠-꜡꞉-꞊꠨-꠫꡴-꡷꣎-꣏꤮-꤯꥟꩜-꩟﬩﴾-﴿﷼-﷽︐-︙︰-﹒﹔-﹦﹨-﹫！-／：-＠［-｀｛-･￠-￦￨-￮￼-�]]/g;
    const allowedPunctuation = /[,.?!"'<>;:]/g;
    sentenceString = sentenceString
      .replace(allowedPunctuation, x => ` ${x} `)
      .replace(punctuationRegEx, '')
      .replace(/(\s){2,}/g, '$1')
      .trim();

    document.getElementById('sentenceString').value = sentenceString;
    const { prepareExercise } = this.props;
    prepareExercise(sentenceString);
  };

  render() {
    return (
      <div className="row">
        <div className="col-12">
          <div className="main-card mb-3 card">
            <div className="card-body">
              <h5 className="card-title">Inserisci la frase </h5>

              <form
                onSubmit={this.handleSubmit}
                className="needs-validation was-validated"
              >
                <div className="input-group">
                  <input
                    id="sentenceString"
                    type="text"
                    className="form-control validate"
                    placeholder="Inserisci una frase"
                    onChange={this.handleChange}
                    required
                  />
                  <div className="input-group-append">
                    <button className="btn btn-success" type="submit">
                      SVOLGI ESERCIZIO
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
