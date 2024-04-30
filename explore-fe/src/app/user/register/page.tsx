/* 
Reference: 
  1. https://react-hook-form.com/get-started
  2. https://github.com/projectsbydan/nextjs-react-form-chakra-ui/blob/master/components/forms/registration-form.tsx
*/

'use client'

import {
  VStack,
  FormControl,
  FormLabel,
  FormErrorMessage,
  FormHelperText,
  Input,
  InputGroup,
  InputLeftElement,
  InputRightElement,
  Button,
  Card,
  CardHeader,
  CardBody,
  CardFooter,
  Heading,
  Icon,
  Box,
  Square,
  HStack,
} from '@chakra-ui/react'

import { EmailIcon, LockIcon } from '@chakra-ui/icons'
import { MdAccountCircle } from "react-icons/md";
import { RiEyeFill, RiEyeOffFill } from "react-icons/ri";
import { FaHandsClapping } from "react-icons/fa6";

import { useForm } from "react-hook-form";
import { useState } from 'react';
import { debounce } from 'lodash';

interface IRegistration {
  name: string;
  email: string;
  pwd: string;
}

export default function Register() {
  const {
    handleSubmit, // handels the form submit event
    register, // ties the inputs to react-form
    formState: { errors, isSubmitting }, // gets errors and "loading" state
  } = useForm<IRegistration>();


  const onRegistered = (data: IRegistration) => {
    fetch('http://localhost:8080/user/register', { // TODO: need op
      method: 'POST',
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    });
  };

  const debouncedOnRegistered = debounce(onRegistered, 2000);

  const [show, setShow] = useState(false);
  const handleClick = () => setShow(!show);

  return (
    <Box className="flex min-h-screen flex-col items-center justify-between p-24" bgGradient='linear(to-r, green.200, pink.500)'>
      <Card w='405px' variant='elevated' bg='gray.50'>
        <CardHeader>
          <HStack>
            <Heading size='md'>Welcome to Explore</Heading>
            <Square><Icon as={FaHandsClapping} color='orange' w={5} h={5} /></Square>
          </HStack>
        </CardHeader>
        <form onSubmit={handleSubmit(debouncedOnRegistered)} noValidate>
          <CardBody>
            <VStack>
              {/* 
                Name input 
              */}
              <FormControl isRequired isInvalid={errors.name ? true : undefined}>
                <FormLabel htmlFor='name'>Name</FormLabel>
                <InputGroup>
                  <InputLeftElement pointerEvents='none'>
                    <Icon as={MdAccountCircle} color='gray.600'></Icon>
                  </InputLeftElement>
                  <Input id='name' type='text' errorBorderColor='crimson'
                    {...register("name", { required: "Name is required." })}
                  />
                </InputGroup>
                <FormErrorMessage>{errors.name && errors.name.message}</FormErrorMessage>
              </FormControl>
              {/*
                Email input
              */}
              <FormControl isRequired isInvalid={errors.email ? true : undefined}>
                <FormLabel htmlFor='email'>Email</FormLabel>
                <InputGroup>
                  <InputLeftElement pointerEvents='none'>
                    <EmailIcon color='gray.600' />
                  </InputLeftElement>
                  <Input id='email' type='email' {...register("email", {
                    required: "Email is required.",
                    pattern: { value: /[A-Za-z0-9\._%+\-]+@[A-Za-z0-9\.\-]+\.[A-Za-z]{2,}/, message: "Email is invalid." },
                  })} />
                </InputGroup>
                <FormErrorMessage>{errors.email && errors.email.message}</FormErrorMessage>
              </FormControl>
              {/*
                Password input
              */}
              <FormControl isRequired isInvalid={errors.pwd ? true : undefined}>
                <FormLabel htmlFor='pwd'>Password</FormLabel>
                <InputGroup size='md'>
                  <InputLeftElement pointerEvents='none'>
                    <LockIcon color='gray.600' />
                  </InputLeftElement>
                  <Input id='pwd' type={show ? 'text' : 'password'} {...register("pwd", {
                    required: "Password is required.",
                    pattern: {
                      value: /^(\S)(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[~`!@#$%^&*()--+={}\[\]|\\:;"'<>,.?/_])[a-zA-Z0-9~`!@#$%^&*()--+={}\[\]|\\:;"'<>,.?/_]{10,16}$/,
                      message: "<b>The rule of setting password</b>:\n1. Password must not contain any whitespace.\n2. Password must contain uppercases, lowercases, numeric characters and special characters (~`!@#$%^&*()--+={}[]|\:;\"'<>,.?/_).\n3. Password's length must between 10 to 16.",
                    },
                  })} />
                  <InputRightElement width='4.5rem'>
                    <Button h='1.75rem' size='sm' onClick={handleClick}>
                      {show ? < Icon as={RiEyeFill} color='gray.600' /> : < Icon as={RiEyeOffFill} color='gray.600' />}
                    </Button>
                  </InputRightElement>
                </InputGroup>
                <FormHelperText></FormHelperText>
                <FormErrorMessage whiteSpace='pre-wrap' wordBreak='break-word'>{errors.pwd && errors.pwd.message}</FormErrorMessage>
              </FormControl>
            </VStack>
          </CardBody>
          <CardFooter>
            {/*
              Submit button
            */}
            <Button type='submit' colorScheme='teal' variant='solid' width='100%' size='md' isLoading={isSubmitting}>
              Register
            </Button>
          </CardFooter>
        </form>
      </Card>
    </Box >
  );
}
