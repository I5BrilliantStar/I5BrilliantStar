// Chakra imports
import {
  Button,
  Flex,
  Text,
  useColorModeValue,
} from "@chakra-ui/react";
import Card from "components/card/Card.js";
// Custom components
import React, { useRef, useState } from 'react';



import Webcam from "react-webcam";
export default function WeeklyRevenue(props) {
  const { ...rest } = props;

  const webcamRef = useRef(null);
  const [refreshKey, setRefreshKey] = useState(0);

  const refreshCamera = () => {
    setRefreshKey(prevKey => prevKey + 1);
  };

  // Chakra Color Mode
  const textColor = useColorModeValue("secondaryGray.900", "white");
  return (
    <Card
      justifyContent='center'
      align='center'
      direction='column'
      w='100%'
      mb='0px'
      {...rest}>
        <Flex align='center' w='100%' px='15px' py='10px'>
        <Text
          me='auto'
          color={textColor}
          fontSize='xl'
          fontWeight='700'
          lineHeight='100%'>
          AICamera
        </Text>
        </Flex>
      <Flex justify='space-between' ps='0px' pe='20px' pt='5px'>
        <Flex align='center' w='100%'>
          <Webcam
        key={refreshKey} // 키값을 변경하여 웹캠 컴포넌트를 다시 렌더링
        ref={webcamRef}
        mirrored={true}
        screenshotFormat="image/jpeg"
        audio={false}
        videoConstraints={{
          width: 1280,
          height: 720,
          facingMode: 'user',
        }}
        style={{
          width: '100%',
          height: 'auto',
        }}
        // 라즈베리 파이 웹 서버의 웹캠 스트림 엔드포인트에 연결
        src="http://10.10.10.217:5900/webcam_feed"
      />
        </Flex>
      </Flex>
      <Flex w='100%' flexDirection={{ base: "column", lg: "row" }}>
        <Flex flexDirection='column' me='20px' mt='28px'>
          <Flex align='center' mb='20px'>
          <Button
            backgroundColor="brand.500" // 원하는 배경색
            color="white" // 흰색 글씨색
            fontSize="sm"
            fontWeight="500"
            mt="4px"
            mr="12px"
            _hover={{ backgroundColor: "brand.600" }} // 마우스 호버 시 배경색 변경
            onClick={refreshCamera}
          >
            Refresh Camera
          </Button>
          </Flex>
        </Flex>
      </Flex>
    </Card>
  );
}
