import React from 'react';
import { BsFillPersonFill, BsChatLeftDots, BsSuitHeart } from 'react-icons/bs';
import { useNavigate } from 'react-router-dom';
import dummy from '../../../../../assets/data/dummy.json';
import { Bottom, CardContainer, Middle, Top } from './style';

const Card = () => {
	const dateSortedCard = dummy.study.sort((a, b) => {
		return new Date(b.created_at).valueOf() - new Date(a.created_at).valueOf();
	});

	// 왜 이것만 켰다 하면 바로 좋아요순 정렬이 되는건지...
	// const likesSortedCard = dummy.study.sort((a, b) => {
	// 	return b.likes - a.likes;
	// });

	// 만약 최신순의 버튼이 눌렸다면(=>최신순버튼이 눌린 이벤트를 가져오기) dateSortedCard로 맵을 돌리고
	// 좋아요순의 버튼이 눌렸다면 likesSoredCard로 맵을 돌려라

	// 직업 버튼 눌린것 filtering

	const navigate = useNavigate();

	return (
		<>
			{dateSortedCard.map((item) => (
				<CardContainer key={item.id}>
					<div className="box">
						<Top>
							<div>{item.state}</div>
							<div>{item.created_at}</div>
						</Top>
						<Middle>
							<div>
								<h3 onClick={() => navigate(`/study/${item.id}`)}>
									{item.title}
								</h3>
								<div>{item.tag}</div>
							</div>
							<p>{item.content}</p>
						</Middle>
						<Bottom>
							<div>
								<div>
									<BsFillPersonFill size={20} />
								</div>
								<span>{item.nickname}</span>
							</div>
							<div>
								<div className="likes">
									<div className="hearticon">
										<BsSuitHeart size={20} />
									</div>
									<span>{item.likes}</span>
								</div>
								<div className="comments">
									<div className="chaticon">
										<BsChatLeftDots size={20} />
									</div>
									<span>{item.comments}</span>
								</div>
							</div>
						</Bottom>
					</div>
				</CardContainer>
			))}
		</>
	);
};
export default Card;
