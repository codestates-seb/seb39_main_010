import React, { useState } from 'react';
import styled from 'styled-components';
import { NewPassword, SortingBoxL, SortingBoxR, SortingBoxW } from './Sorting';

export const MyProfileContainer = styled.div`
	display: flex;
	justify-content: space-between;
	width: 1200px;

	.leftbox {
		width: 180px;
		height: 203px;

		div {
			padding-bottom: 20px;
			margin-bottom: 20px;
			border-bottom: 1px solid #b0b0b0;

			font-size: 20px;
			font-weight: 500;
		}
		li {
			margin-bottom: 13px;
			font-size: 16px;
			font-weight: 500;
		}
		.submenu {
			cursor: pointer;
		}
		.focused {
			color: #3563e9;
		}
	}
`;

const MyProfile = () => {
	const [currentTab, setCurrentTab] = useState(0);
	const profile = [
		{ id: 0, title: '내가 작성한 글', content: <SortingBoxW /> },
		{ id: 1, title: '내가 쓴 댓글', content: <SortingBoxR /> },
		{ id: 2, title: '좋아요 누른 글', content: <SortingBoxL /> },
		{ id: 3, title: '비밀번호 변경', content: <NewPassword /> },
	];
	const selectMenuHandler = (index: number) => {
		setCurrentTab(index);
	};
	const currentmenu = profile[currentTab].content;

	return (
		<MyProfileContainer>
			<div className="leftbox">
				<div>내 프로필</div>
				<ul>
					{profile.map((ele, index) => (
						<li
							key={index}
							className={currentTab === index ? 'submenu focused' : 'submenu'}
							onClick={() => selectMenuHandler(index)}
						>
							{ele.title}
						</li>
					))}
				</ul>
			</div>
			{currentmenu}
		</MyProfileContainer>
	);
};

export default MyProfile;
