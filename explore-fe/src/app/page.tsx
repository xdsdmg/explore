"use client"

import {
  Box, Input, InputGroup, InputLeftElement, Flex,
  Spacer, Heading, Link, Divider, VStack, Card, CardBody, Grid, Text,
} from "@chakra-ui/react";
import { SearchIcon } from "@chakra-ui/icons";
import { useEffect, useState } from "react";
import { ArrayResponseBodyIF } from "./model/base"

interface TopicIF {
  title: string,
  userName: string,
  createdAt: number,
}

const Topic = (props: TopicIF) => {
  console.log(props.createdAt)

  let timeStamp = new Date(props.createdAt).toLocaleString()

  return (
    <Card size='sm' w='800px' variant='outline'>
      <CardBody>
        <Link>{props.title}</Link>
        <Text fontSize='xs'>by {props.userName} at {timeStamp}</Text>
      </CardBody>
    </Card>
  )
}

export default function Home() {
  const [topics, setTopics] = useState([] as TopicIF[])

  const fetchTopic = () => {
    fetch('/api/topic/list', {
      method: 'GET',
    })
      .then((res) => res.json())
      .then((body: ArrayResponseBodyIF<TopicIF>) => { setTopics(body.data) });
  }

  useEffect(() => { fetchTopic(); }, [])

  return (
    <Grid minHeight='100%' templateRows={'60px auto 120px'}>
      <Box>
        <Flex alignItems='center' gap='8' padding={'5px 150px'} bg='white'>
          <Box p='2'>
            <Heading size='md'><Link href='/'>Explore</Link></Heading>
          </Box>
          <Spacer />
          <InputGroup w={250} size='sm'>
            <InputLeftElement pointerEvents='none'>
              <SearchIcon color='gray.600' />
            </InputLeftElement>
            <Input type='tel' placeholder='Search Explore' />
          </InputGroup>
          <Link>About Explore</Link>
          <Link href='/user/login'>Login</Link>
        </Flex>
        <Divider />
      </Box>
      <Flex justifyContent='center'>
        <VStack>
          {topics.map((topic, index) => {
            return <Topic title={topic.title} userName={topic.userName} createdAt={topic.createdAt}></Topic>
          })}
        </VStack>
      </Flex>
      <Box>
        <Divider color='gray' colorScheme='gray' />
        <Flex justifyContent='center' bg='white'>
          <VStack w='800px'>
            <Flex justifyContent='left' alignItems='center' w='100%' gap={4} padding={'5px 0 0 2px'}>
              <Link color='gray.500' as='b'>About</Link>
              <Link color='gray.500' as='b'>Help</Link>
            </Flex>
            <Box w='100%' padding={'2px 0 2px 0'}>
              <Text fontSize='sm'>Hi! Welcome to Explore.</Text>
              <Text fontSize='sm' as='cite'>Do have faith in what you love.</Text>
            </Box>
          </VStack>
        </Flex>
      </Box >
    </Grid >
  );
}
