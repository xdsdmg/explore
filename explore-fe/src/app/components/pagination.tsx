import { ChevronLeftIcon, ChevronRightIcon } from "@chakra-ui/icons";
import { Button, HStack, IconButton } from "@chakra-ui/react";
import { useEffect, useState } from "react";

const defaultPageSize = 20;
const defaultBtnNum = 5;

interface PageNumBtnProps {
  setPos: any,
  num: number,
  isActive: boolean,
  setElements: any,
  pageTotal: number,
}

function PageNumBtn(props: PageNumBtnProps) {
  const handleClick = () => {
    props.setPos(props.num);

    let elements: PaginationElement[] = [];
    let gap: number = (defaultBtnNum - 1) / 2;

    let end: number = props.num + gap > defaultBtnNum ? props.num + gap : defaultBtnNum
    end = end < props.pageTotal ? end : props.pageTotal;

    console.log('gap: ', gap);
    console.log('end: ', end);

    if (props.num > defaultBtnNum) {
      elements.push({ type: 'num', num: 1 });
      elements.push({ type: 'ellipsis' });

      for (let i = props.num - gap; i <= end; i++) {
        elements.push({ type: 'num', num: i });
      }
    } else {
      for (let i = 1; i <= end; i++) {
        elements.push({ type: 'num', num: i });
      }
    }

    if (end < props.pageTotal - 1) {
      elements.push({ type: 'ellipsis' });
      elements.push({ type: 'num', num: props.pageTotal });
    } else {
      for (let i = end; i <= props.pageTotal; i++) {
        elements.push({ type: 'num', num: props.pageTotal });
      }
    }

    props.setElements(elements);
  }

  return <Button variant='outline' size='xs' isActive={props.isActive} onClick={handleClick}>{props.num}</Button>;
}

interface PageEllipsisBtnProps {
  setPos: any,
  pos: number,
  direction: boolean,
  defaultBtnNum: number,
}

function PageEllipsisBtn(props: PageEllipsisBtnProps) {
  const handleClick = () => {
    if (props.direction) {
      props.setPos(props.pos + props.defaultBtnNum)
    }
  }

  return <Button variant='outline' size='xs' onClick={handleClick}>...</Button>;
}

interface PaginationProps {
  total: number,
  pageSize?: number,
}

interface PaginationElement {
  type: 'num' | 'ellipsis',
  num?: number,
}

export default function Pagination(props: PaginationProps) {
  const [pos, setPos] = useState(1);

  const pageSize = props.pageSize ? props.pageSize : defaultPageSize;
  const pageTotal = Math.ceil(props.total / pageSize);

  let initialElements: PaginationElement[] = [];
  if (pageTotal <= defaultBtnNum + 1) {
    for (let i = 1; i <= pageTotal; i++) {
      initialElements.push({ type: 'num', num: i });
    }
  } else {
    for (let i = 1; i <= defaultBtnNum; i++) {
      initialElements.push({ type: 'num', num: i });
    }
    initialElements.push({ type: 'ellipsis' });
    initialElements.push({ type: 'num', num: pageTotal });
  }

  const [elements, setElements] = useState(initialElements);

  const PageBtnRender = () => {
    return (
      <HStack>
        {elements.map((e, _) => {
          return e.type == 'num' ?
            <PageNumBtn
              num={e.num ? e.num : 0}
              isActive={pos === e.num}
              setPos={setPos}
              setElements={setElements}
              pageTotal={pageTotal}
            />
            :
            <PageEllipsisBtn direction={true} setPos={setPos} pos={pos} defaultBtnNum={defaultBtnNum} />
        })}
      </HStack>
    );
  }

  return (<HStack>
    <IconButton aria-label='ChevronLeft' icon={<ChevronLeftIcon />} variant='outline' size='xs' />
    <PageBtnRender />
    <IconButton aria-label='ChevronRight' icon={<ChevronRightIcon />} variant='outline' size='xs' />
  </HStack>);
}