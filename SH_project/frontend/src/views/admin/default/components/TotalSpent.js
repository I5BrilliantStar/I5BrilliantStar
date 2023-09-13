import React, { useState, useEffect } from "react";
import axios from "axios";
import {
  Button,
  Flex,
  Text,
  useColorModeValue,
} from "@chakra-ui/react";
import Card from "components/card/Card.js";

export default function TotalSpent(props) {
  const { ...rest } = props;
  const [fetchImage, setFetchImage] = useState(true);
  const [data, setData] = useState({
    qr_data: "",
    qr_image: "",
    label: "",
  });

  const toggleFetchImage = () => {
    setFetchImage((prevState) => !prevState);
  };

  useEffect(() => {
    const fetchData = async () => {
      if (fetchImage) {
        try {
          const response = await axios.get("http://10.10.10.111:5000/stream");
          setData(response.data);
        } catch (error) {
          console.error("Error fetching data:", error);
        }
      }
    };

    const intervalId = setInterval(fetchData, 33);

    return () => {
      clearInterval(intervalId);
    };
  }, [fetchImage]);

  const textColor = useColorModeValue("secondaryGray.900", "white");

  return (
    <Card
      justifyContent="center"
      align="center"
      direction="column"
      w="100%"
      mb="0px"
      {...rest}
    >
      <Flex align="center" w="100%" px="15px" py="10px">
        <Text
          me="auto"
          color={textColor}
          fontSize="xl"
          fontWeight="700"
          lineHeight="100%"
        >
          장비 모니터링
        </Text>
      </Flex>
    </Card>
  );
}
