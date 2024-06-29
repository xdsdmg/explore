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

export default function Navigation() {
  const fetchUserInfo = () => {
    fetch(`/api/user/auth/info`, { method: 'GET' })
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
                <MenuItem><a href='/topic/create'>Create a topic</a></MenuItem>
                <MenuItem>Logout</MenuItem>
              </MenuList>
            </Menu>
        }
      </Flex>
      <Divider />
    </Box>
  );
}