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
              onClick={() => onClick(prev)}
              disabled={number === 0}
            >
              {_translator('pagination_prev', language)}
            </button>
          </li>

          {number > 1 && (
            <li className={cssClassFirst}>
              <button
                className="page-link"
                type="button"
                onClick={() => onClick(first)}
                disabled={number === 0}
              >
                1
              </button>
            </li>
          )}
          {number > 2 && totalPages > 3 && (
            <li className="page-item disabled">
              <button className="page-link" type="button" disabled>
                ...
              </button>
            </li>
          )}
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
          {number < totalPages - 2 && (
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
          {totalPages > 3 && number + 3 < totalPages && (
            <li className="page-item disabled">
              <button className="page-link" type="button" disabled>
                ...
              </button>
            </li>
          )}
          {number < totalPages - 1 && (
            <li className={cssClassLast}>
              <button
                className="page-link"
                type="button"
                onClick={() => onClick(last)}
                disabled={number === totalPages - 1}
              >
                {totalPages}
              </button>
            </li>
          )}

          <li className={cssClassLast}>
            <button
              className="page-link"
              type="button"
              onClick={() => onClick(next)}
              disabled={number >= totalPages}
            >
              {_translator('pagination_next', language)}
            </button>
          </li>
        </ul>
      </nav>
    );
  }
  return <React.Fragment />;
};

export default Pagination;
