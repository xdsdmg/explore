import { ChevronLeftIcon, ChevronRightIcon } from "@chakra-ui/icons";
import { Button, HStack, IconButton } from "@chakra-ui/react";
import { useState } from "react";

const defaultPageSize = 20;
const defaultBtnNum = 3;

interface PageNumBtnProps {
  setPos: any,
  num: number,
  isActive: boolean,
  setElements: any,
  pageTotal: number,
}

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

  if (begin < 1) {
    begin = 1;
    end = begin + 2 * gap <= pageTotal ? begin + 2 * gap : pageTotal;
  }
  if (end > pageTotal) {
    end = pageTotal;
    begin = end - 2 * gap >= 1 ? end - 2 * gap : 1;
  }

  if (begin > 1) {
    elements.push({ type: 'num', num: 1 });
    if (begin > 3) {
      elements.push({ type: 'ellipsis', direction: false });
    } else if (begin == 3) {
      elements.push({ type: 'num', num: 2 });
    }
  }

  for (let i = begin; i <= end; i++) {
    elements.push({ type: 'num', num: i });
  }

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

function PageNumBtn(props: PageNumBtnProps) {
  const handleClick = () => {
    props.setPos(props.num);
    props.setElements(getElements(props.num, props.pageTotal));
  }

  return <Button variant='outline' size='xs' isActive={props.isActive} onClick={handleClick}>{props.num}</Button>;
}

interface PageEllipsisBtnProps {
  setPos: any,
  pos: number,
  direction: boolean,
  setElements: any,
  pageTotal: number,
}

function PageEllipsisBtn(props: PageEllipsisBtnProps) {
  const handleClick = () => {
    let pos = props.direction ? props.pos + defaultBtnNum : props.pos - defaultBtnNum;
    props.setPos(pos);
    props.setElements(getElements(pos, props.pageTotal));
  }

  return <Button variant='outline' size='xs' onClick={handleClick}>...</Button>;
}

interface PaginationProps {
  total: number,
  pageSize?: number,
}

interface DirectionBtnProps {
  setPos: any,
  pos: number,
  direction: boolean,
  setElements: any,
  pageTotal: number,
}

function DirectionBtn(props: DirectionBtnProps) {
  const handleClick = () => {
    if ((props.direction && props.pos === props.pageTotal) || (!props.direction && props.pos === 1)) {
      return;
    }

    let pos: number = props.direction ? props.pos + 1 : props.pos - 1;
    props.setPos(pos);
    props.setElements(getElements(pos, props.pageTotal));
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

export default function Pagination(props: PaginationProps) {
  const pageSize = props.pageSize ? props.pageSize : defaultPageSize;
  const pageTotal = Math.ceil(props.total / pageSize);

  const [pos, setPos] = useState(1);
  const [elements, setElements] = useState(getElements(1, pageTotal));

  const PageBtnRender = () => {
    return (
      <HStack>
        {elements.map((e, _) => {
          return e.type === 'num' ?
            <PageNumBtn
              num={e.num}
              isActive={pos === e.num}
              setPos={setPos}
              setElements={setElements}
              pageTotal={pageTotal}
            />
            :
            <PageEllipsisBtn
              direction={e.direction}
              setPos={setPos}
              pos={pos}
              setElements={setElements}
              pageTotal={pageTotal}
            />
        })}
      </HStack>
    );
  }

  return (
    <HStack>
      <DirectionBtn
        direction={false}
        setPos={setPos}
        pos={pos}
        setElements={setElements}
        pageTotal={pageTotal}
      />
      <PageBtnRender />
      <DirectionBtn
        direction={true}
        setPos={setPos}
        pos={pos}
        setElements={setElements}
        pageTotal={pageTotal}
      />
    </HStack>
  );
}