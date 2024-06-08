/**
 * Pagination
 */

import { ChevronLeftIcon, ChevronRightIcon } from "@chakra-ui/icons";
import { Button, HStack, IconButton } from "@chakra-ui/react";
import { useEffect, useState } from "react";

const defaultPageSize = 20;
const defaultBtnNum = 3;

/**
 * Get the elements of pagination by postion and the total of pages
 */

interface NumElement {
  readonly type: 'num',
  num: number,
}

interface EllipsisElement {
  readonly type: 'ellipsis',
  direction: boolean,
}

function getElements(pos: number, pageTotal: number): (NumElement | EllipsisElement)[] {
  let elements: (NumElement | EllipsisElement)[] = [];
  let gap: number = (defaultBtnNum - 1) / 2;
  let begin = pos - gap;
  let end = pos + gap;

  /* 
    Calculate begin and end 
  */
  if (begin < 1) {
    begin = 1;
    end = begin + 2 * gap <= pageTotal ? begin + 2 * gap : pageTotal;
  }
  if (end > pageTotal) {
    end = pageTotal;
    begin = end - 2 * gap >= 1 ? end - 2 * gap : 1;
  }

  /*
    Create the previous element
  */
  if (begin > 1) {
    elements.push({ type: 'num', num: 1 });
    if (begin > 3) {
      elements.push({ type: 'ellipsis', direction: false });
    } else if (begin == 3) {
      elements.push({ type: 'num', num: 2 });
    }
  }

  /*
    Create the elements around the pos
  */
  for (let i = begin; i <= end; i++) {
    elements.push({ type: 'num', num: i });
  }

  /*
    Create the trailing elements
  */
  if (end < pageTotal) {
    if (end < pageTotal - 2) {
      elements.push({ type: 'ellipsis', direction: true });
    } else if (end == pageTotal - 2) {
      elements.push({ type: 'num', num: pageTotal - 1 });
    }
    elements.push({ type: 'num', num: pageTotal });
  }

  return elements;
}

/**
 * Number button in pagination
 */

interface PageNumBtnProps {
  num: number,
  isActive: boolean,
  pageTotal: number,
  /* Callback function */
  setPos: any,
  setElements: any,
  callback: any,
}

function PageNumBtn(props: PageNumBtnProps) {
  const handleClick = () => {
    props.setPos(props.num);
    props.setElements(getElements(props.num, props.pageTotal));
    props.callback();
  }

  return <Button variant='outline' size='xs' isActive={props.isActive} onClick={handleClick}>{props.num}</Button>;
}

/**
 * Ellipsis button in pagination
 */

interface PageEllipsisBtnProps {
  pos: number,
  direction: boolean,
  pageTotal: number,
  /* Callback function */
  setPos: any,
  setElements: any,
  callback: any,
}

function PageEllipsisBtn(props: PageEllipsisBtnProps) {
  const handleClick = () => {
    let pos = props.direction ? props.pos + defaultBtnNum : props.pos - defaultBtnNum;
    props.setPos(pos);
    props.setElements(getElements(pos, props.pageTotal));
    props.callback();
  }

  return <Button variant='outline' size='xs' onClick={handleClick}>...</Button>;
}

/**
 * Direction button in pagination
 */

interface DirectionBtnProps {
  pos: number,
  direction: boolean,
  pageTotal: number,
  /* Callback function */
  callback: any,
  setElements: any,
  setPos: any,
}

function DirectionBtn(props: DirectionBtnProps) {
  const handleClick = () => {
    if ((props.direction && props.pos === props.pageTotal) || (!props.direction && props.pos === 1)) {
      return;
    }

    let pos: number = props.direction ? props.pos + 1 : props.pos - 1;
    props.setPos(pos);
    props.setElements(getElements(pos, props.pageTotal));
    props.callback();
  }

  return (
    <IconButton
      variant='outline'
      size='xs'
      aria-label={props.direction ? 'ChevronRight' : 'ChevronLeft'}
      icon={props.direction ? <ChevronRightIcon /> : <ChevronLeftIcon />}
      onClick={handleClick}
      isDisabled={props.direction ? props.pos === props.pageTotal : props.pos === 1}
    />
  );
}

/**
 * Pagination
 */

interface PaginationProps {
  total: number,
  pageSize?: number,
  callback: any,
}

export default function Pagination(props: PaginationProps) {
  const pageSize = props.pageSize ? props.pageSize : defaultPageSize;
  const pageTotal = Math.ceil(props.total / pageSize);

  if (pageTotal == 1) {
    return <></>
  }

  const [pos, setPos] = useState(1);
  const [elements, setElements] = useState(getElements(pos, pageTotal));

  useEffect(() => {
    setElements(getElements(pos, pageTotal));
  }, [props.total])

  return (
    <HStack>
      <DirectionBtn
        direction={false}
        setPos={setPos}
        pos={pos}
        setElements={setElements}
        pageTotal={pageTotal}
        callback={props.callback}
      />
      <HStack>
        {elements.map((e, index) => {
          return e.type === 'num' ?
            <PageNumBtn
              key={index}
              num={e.num}
              isActive={pos === e.num}
              setPos={setPos}
              setElements={setElements}
              pageTotal={pageTotal}
              callback={props.callback}
            />
            :
            <PageEllipsisBtn
              key={index}
              direction={e.direction}
              setPos={setPos}
              pos={pos}
              setElements={setElements}
              pageTotal={pageTotal}
              callback={props.callback}
            />
        })}
      </HStack>
      <DirectionBtn
        direction={true}
        setPos={setPos}
        pos={pos}
        setElements={setElements}
        pageTotal={pageTotal}
        callback={props.callback}
      />
    </HStack>
  );
}