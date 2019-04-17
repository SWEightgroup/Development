import React from 'react';
import _translator from '../../helpers/Translator';

const Pagination = props => {
  const {
    first,
    last,
    next,
    prev,
    number,
    totalPages,
    language,
    onClick
  } = props;
  console.log(': props', props);
  const cssClassFirst = `page-item${number === 0 ? ' disabled' : ''}`;
  const cssClassLast = `page-item${
    number === totalPages - 1 ? ' disabled' : ''
  }`;

  if (totalPages && totalPages > 1) {
    return (
      /*
    pagination_first : 'Primo',
    pagination_last : 'Ultimo',
    pagination_previous : 'Successivo',
    pagination_next : 'Precedente', */
      <nav aria-label="Page navigation example">
        <ul className="pagination justify-content-center">
          <li className={cssClassFirst}>
            <button
              className="page-link"
              type="button"
              onClick={() => onClick(first)}
              disabled={number === 0}
            >
              {_translator('pagination_first', language)}
            </button>
          </li>
          {number > 0 && (
            <li className="page-item ">
              <button
                className="page-link"
                type="button"
                onClick={() => onClick(prev)}
              >
                {number}
              </button>
            </li>
          )}
          <li className="page-item disabled ">
            <button className="page-link" type="button">
              {number + 1}
            </button>
          </li>
          {number < totalPages - 1 && (
            <li className="page-item ">
              <button
                className="page-link"
                type="button"
                onClick={() => onClick(next)}
              >
                {number + 2}
              </button>
            </li>
          )}
          <li className={cssClassLast}>
            <button
              className="page-link"
              type="button"
              onClick={() => onClick(last)}
              disabled={number === totalPages - 1}
            >
              {_translator('pagination_last', language)}
            </button>
          </li>
        </ul>
      </nav>
    );
  }
  return <React.Fragment />;
};

export default Pagination;
