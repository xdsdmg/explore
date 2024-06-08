/**
 * Navigation bar
 */

import {
  Box, Input, InputGroup, InputLeftElement, Flex, Spacer, Heading, Link, Divider
} from "@chakra-ui/react";
import { SearchIcon } from "@chakra-ui/icons";
import { useEffect, useState } from "react";
import { UserInfoIF } from "../model/user";
import { SingleResponseBodyIF } from "../model/base";
import { getCookie } from "../utils/cookie";

export default function Navigation() {
  const fetchUserInfo = () => {
    const token = getCookie('Jwe-Token');

    fetch(`/api/user/info?jwe_token=${token}`, {
      method: 'GET',
    })
      .then((res) => res.json())
      .then((body: SingleResponseBodyIF<UserInfoIF>) => {
        setUserInfo(body.data);
      });
  }

  useEffect(() => { fetchUserInfo(); }, []);

  const [userInfo, setUserInfo] = useState<null | UserInfoIF>(null);

  return (
    <Box w='100%'>
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
        {
          userInfo == null ?
            <Link href='/user/login'>Login</Link>
            :
            <Link href='/user/login'>{userInfo.name}</Link>
        }
      </Flex>
      <Divider />
    </Box>
  );
}