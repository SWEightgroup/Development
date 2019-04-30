import { toast } from 'react-toastify';
import { confirmAlert } from 'react-confirm-alert';

export const removePunctuation = sentenceString => {
  const sentence = sentenceString;
  // const punctuationRegEx = /[--@[-`{-~¡-©«-¬®-±´¶-»¿×÷˂-˅˒-˟˥-˫˭˯-˿͵΄-΅·϶҂՚-՟-֊־׀׃׆׳-״؆-؏؛؞-؟٪-٭۔۩۽-۾܀-܍߶-߹।-॥॰৲-৳৺૱୰௳-௺౿ೱ-ೲ൹෴฿๏๚-๛༁-༗༚-༟༴༶༸༺-༽྅྾-࿅࿇-࿌࿎-࿔၊-၏႞-႟჻፠-፨᎐-᎙᙭-᙮᚛-᚜᛫-᛭᜵-᜶។-៖៘-៛᠀-᠊᥀᥄-᥅᧞-᧿᨞-᨟᭚-᭪᭴-᭼᰻-᰿᱾-᱿᾽᾿-῁῍-῏῝-῟῭-`´-῾\u2000-\u206e⁺-⁾₊-₎₠-₵℀-℁℃-℆℈-℉℔№-℘℞-℣℥℧℩℮℺-℻⅀-⅄⅊-⅍⅏←-⏧␀-␦⑀-⑊⒜-ⓩ─-⚝⚠-⚼⛀-⛃✁-✄✆-✉✌-✧✩-❋❍❏-❒❖❘-❞❡-❵➔➘-➯➱-➾⟀-⟊⟌⟐-⭌⭐-⭔⳥-⳪⳹-⳼⳾-⳿⸀-\u2e7e⺀-⺙⺛-⻳⼀-⿕⿰-⿻\u3000-〿゛-゜゠・㆐-㆑㆖-㆟㇀-㇣㈀-㈞㈪-㉃㉐㉠-㉿㊊-㊰㋀-㋾㌀-㏿䷀-䷿꒐-꓆꘍-꘏꙳꙾꜀-꜖꜠-꜡꞉-꞊꠨-꠫꡴-꡷꣎-꣏꤮-꤯꥟꩜-꩟﬩﴾-﴿﷼-﷽︐-︙︰-﹒﹔-﹦﹨-﹫！-／：-＠［-｀｛-･￠-￦￨-￮￼-�]]/g;
  const allowedPunctuation = /[,.?!"'<->;:]/g;
  const cleanString = sentence
    .replace(allowedPunctuation, x => ` ${x} `)
    .replace(/[^\x20-\x7E]+/g, '')
    .replace(/(\s){2,}/g, '$1')
    .trim();
  return cleanString;
};

export const filterPuntuaction = array => {
  return array.filter(element => element.charAt(0) !== 'F');
};

export const _toastSuccess = text =>
  text &&
  toast.success(text, {
    position: 'bottom-right',
    autoClose: 5000,
    hideProgressBar: true,
    closeOnClick: true,
    pauseOnHover: true,
    draggable: true,
    className: {
      color: '#343a40',
      minHeight: '60px',
      borderRadius: '8px',
      background: '#2FEDAD',
      boxShadow: '2px 2px 20px 2px rgba(0,0,0,0.3)'
    }
  });

export const _toastError = text =>
  text &&
  toast.error(text, {
    position: 'bottom-right',
    autoClose: 5000,
    hideProgressBar: true,
    closeOnClick: true,
    pauseOnHover: true,
    draggable: true,
    className: {
      color: '#343a40',
      minHeight: '60px',
      borderRadius: '8px',
      background: '#2FEDAD',
      boxShadow: '2px 2px 20px 2px rgba(0,0,0,0.3)'
    }
  });

export const _confirmAlert = (
  { title, message, yes, no },
  onConfirm,
  param
) => {
  confirmAlert({
    title: title !== null ? title : '',
    message: message !== null ? message : '',
    buttons: [
      {
        label: yes && yes.length > 0 ? yes : 'si',
        onClick: () => onConfirm(param)
      },
      {
        label: no && no.length > 0 ? no : 'no'
      }
    ]
  });
};
