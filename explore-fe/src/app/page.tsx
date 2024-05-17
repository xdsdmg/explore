"use client"

import {
  Box, Input, InputGroup, InputLeftElement, Button, Flex,
  Spacer, Heading, Link, Divider, VStack, Card, CardBody, CardFooter, Grid, Text,
  Container
} from "@chakra-ui/react";
import { SearchIcon } from "@chakra-ui/icons";

interface TopicIF {
  title: string,
  username: string,
  time: string,
}

const Topic = (props: TopicIF) => {
  return (
    <Card size='sm' w='800px'>
      <CardBody>
        <Link>{props.title}</Link>
        <Text fontSize='xs'>by {props.username} at {props.time}</Text>
      </CardBody>
    </Card>
  )
}

export default function Home() {
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
          <Topic title='This is the title of a topic' username='ZhangChi' time='2024-05-20 19:41:33 (UTC+8)' />
          <Topic title='This is the title of a topic' username='ZhangChi' time='2024-05-20 19:41:33 (UTC+8)' />
          <Topic title='This is the title of a topic' username='ZhangChi' time='2024-05-20 19:41:33 (UTC+8)' />
          <Topic title='This is the title of a topic' username='ZhangChi' time='2024-05-20 19:41:33 (UTC+8)' />
          <Topic title='This is the title of a topic' username='ZhangChi' time='2024-05-20 19:41:33 (UTC+8)' />
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
              <Text fontSize='sm' as='cite'>In the world of computers, anyone who can program can own the world.</Text>
            </Box>
          </VStack>
        </Flex>
      </Box >
    </Grid >
  );
}
