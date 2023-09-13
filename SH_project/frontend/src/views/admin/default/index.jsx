import React, { useState } from 'react';
import {
  Box,
  Flex,
  Button,
  Select,
  SimpleGrid,
  useColorModeValue,
  Avatar,
  FormLabel,
  Icon,
} from "@chakra-ui/react";
import {
  MdAddTask,
  MdAttachMoney,
  MdFileCopy,
} from "react-icons/md";
import MiniStatistics from "components/card/MiniStatistics";
import IconBox from "components/icons/IconBox";
import TotalSpent from "views/admin/default/components/TotalSpent";
import WeeklyRevenue from "views/admin/default/components/WeeklyRevenue";
import CheckTable from "views/admin/default/components/CheckTable";
import ComplexTable from "views/admin/default/components/ComplexTable";
import DailyTraffic from "views/admin/default/components/DailyTraffic";
import PieCard from "views/admin/default/components/PieCard";
import Tasks from "views/admin/default/components/Tasks";
import MiniCalendar from "components/calendar/MiniCalendar";
import {
  columnsDataCheck,
  columnsDataComplex,
} from "views/admin/default/variables/columnsData";
import tableDataCheck from "views/admin/default/variables/tableDataCheck.json";
import tableDataComplex from "views/admin/default/variables/tableDataComplex.json";
import failImage from "assets/img/dashboards/fail.png"
import repairImg from "assets/img/dashboards/repair.png"
import percentImg from "assets/img/dashboards/percent.png"


export default function UserReports() {
  const brandColor = useColorModeValue("brand.500", "white");
  const boxBg = useColorModeValue("secondaryGray.300", "whiteAlpha.100");
  const [isOn, setIsOn] = useState(true);

  const toggleButton = () => {
    setIsOn(!isOn);
  };

  const buttonStyle = {
    backgroundColor: isOn ? 'green' : 'red',
    color: 'white',
    fontSize: 'sm',
    fontWeight: '500',
    mt: '4px',
    mr: '12px',
    _hover: { backgroundColor: isOn ? 'green.600' : 'red.600' },
  };

  const [productCount, setProductCount] = useState(0);
  const [defectiveCount, setDefectiveCount] = useState(0);

  const handleProductRecognition = () => {
    setProductCount(productCount + 1);
  };

  const handleDefectiveRecognition = () => {
    setDefectiveCount(defectiveCount + 1);
  };

  const defectiveRatio = (defectiveCount / productCount) * 100;

  return (
    <Box pt={{ base: "130px", md: "80px", xl: "80px" }}>
      <SimpleGrid
        columns={{ base: 1, md: 2, lg: 3, "2xl": 6 }}
        gap='20px'
        mb='20px'
      >
        <MiniStatistics
          startContent={
            <div style={{ display: 'flex', alignItems: 'center' }}>
              <div style={{ marginTop: '4px', marginBottom: '4px' }}>
                <Button
                  onClick={toggleButton}
                  style={{ ...buttonStyle, width: '80px' }}
                >
                  {isOn ? 'On' : 'Off'}
                </Button>
              </div>
            </div>
          }
          name='장비 제어 버튼'
          value='!주의!'
        />
        <MiniStatistics
          startContent={
            <IconBox
              w='56px'
              h='56px'
              bg={boxBg}
              icon={
                <Icon w='32px' h='32px' as={MdAttachMoney} color={brandColor} />
              }
            />
          }
          name='Total $'
          value={defectiveCount}
        />
        <MiniStatistics
         startContent={
          <IconBox
            w='56px'
            h='56px'
            bg={boxBg}
            icon={
              <img src={failImage} alt="Fail" width="32px" height="32px" style={{ transform: 'rotate(180deg) scaleX(-1)'}} />
            }
          />
        }
         name='상품개수' value={productCount} />
        <MiniStatistics
          startContent={
            <IconBox
              w='56px'
              h='56px'
              bg={boxBg}
              icon={
                <img src={failImage} alt="Fail" width="32px" height="32px" />
              }
            />
          }
          name='불량품 개수'
          value={defectiveCount}
        />
        <MiniStatistics
          startContent={
            <IconBox
              w='56px'
              h='56px'
              bg={boxBg}
              icon={
                <img src={repairImg} alt="Fail" width="32px" height="32px" />
              }
            />
          }
          name='박스 불량 체크'
          value={defectiveCount}
        />
        <MiniStatistics
  startContent={
    <IconBox
      w='56px'
      h='56px'
      bg={boxBg}
      icon={
        <img src={percentImg} alt="Fail" width="32px" height="32px" />
      }
    />
  }
  name='불량품 비율'
  value={`${defectiveRatio.toFixed(2)}%`} // 불량품 비율을 표시
/>
      </SimpleGrid>

      <SimpleGrid columns={{ base: 1, md: 2, xl: 2 }} gap='20px' mb='20px'>
        <TotalSpent />
        <WeeklyRevenue />
      </SimpleGrid>
      <SimpleGrid columns={{ base: 1, md: 1, xl: 2 }} gap='20px' mb='20px'>
        <CheckTable columnsData={columnsDataCheck} tableData={tableDataCheck} />
        <SimpleGrid columns={{ base: 1, md: 2, xl: 2 }} gap='20px'>
          <DailyTraffic />
          <PieCard />
        </SimpleGrid>
      </SimpleGrid>
      <SimpleGrid columns={{ base: 1, md: 1, xl: 2 }} gap='20px' mb='20px'>
        <ComplexTable
          columnsData={columnsDataComplex}
          tableData={tableDataComplex}
        />
        <SimpleGrid columns={{ base: 1, md: 2, xl: 2 }} gap='20px'>
          <Tasks />
          <MiniCalendar h='100%' minW='100%' selectRange={false} />
        </SimpleGrid>
      </SimpleGrid>
    </Box>
  );
}
