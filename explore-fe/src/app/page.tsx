"use client"

import {
  Flex, Link, VStack, Card, CardBody, Grid, Text,
} from "@chakra-ui/react";
import { useEffect, useState } from "react";
import { ArrayResponseBodyIF } from "./model/base"
import Pagination from "./components/pagination";
import Navigation from "./components/navigation";
import Footer from "./components/footer";

interface TopicIF {
  title: string,
  userName: string,
  createdAt: number,
}

const Topic = (props: TopicIF) => {
  return (
    <Card size='sm' w='800px' variant='outline'>
      <CardBody>
        <Link>{props.title}</Link>
        <Text fontSize='xs'>by {props.userName} at {new Date(props.createdAt).toLocaleString()}</Text>
      </CardBody>
    </Card>
  )
}

export default function Home() {
  const [body, setBody] = useState({ code: 0, data: [], total: 0, msg: '' } as ArrayResponseBodyIF<TopicIF>)

  const fetchTopic = () => {
    fetch('/api/topic/list', {
      method: 'GET',
    })
      .then((res) => res.json())
      .then((body: ArrayResponseBodyIF<TopicIF>) => {
        setBody(body);
      });
  }

  useEffect(() => { fetchTopic(); }, [])

  return (
    <Grid minHeight='100%' templateRows={'60px auto 120px'}>
      <Navigation />
      <Flex justifyContent='center'>
        <VStack>
          {
            body.data.map((topic, index) =>
              <Topic
                key={index}
                title={topic.title}
                userName={topic.userName}
                createdAt={topic.createdAt}
              />
            )
          }
          <Pagination total={body.total} callback={fetchTopic} />
        </VStack>
      </Flex>
      <Footer />
    </Grid >
  );
}
