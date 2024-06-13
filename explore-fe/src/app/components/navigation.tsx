/**
 * Navigation bar
 */

import {
  Box, Input, InputGroup, InputLeftElement, Flex, Spacer, Heading, Link, Divider,
  Menu, MenuButton, MenuList, MenuItem
} from "@chakra-ui/react";
import { SearchIcon, ChevronDownIcon } from "@chakra-ui/icons";
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
    <Box w='100%' h='50px'>
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
            <Menu>
              <MenuButton>
                {userInfo.name} <ChevronDownIcon />
              </MenuButton>
              <MenuList>
                <MenuItem>Create a topic</MenuItem>
                <MenuItem>Logout</MenuItem>
              </MenuList>
            </Menu>
        }
      </Flex>
      <Divider />
    </Box>
  );
}