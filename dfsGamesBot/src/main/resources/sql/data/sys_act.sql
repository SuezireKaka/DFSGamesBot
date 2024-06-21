insert into sys_act(act, area, type, info)
values ('SM', 'System', 'Manage', '시스템 전체를 관리합니다'),
('AV', 'Anonym', 'View', '다른 사람의 메모를 볼 수 있는 익명 활동입니다'),
('PM', 'Private', 'Manage', '자신에 대한 모든 것을 관리합니다'),
('GC', 'Group', 'Create', '자신의 그룹을 만들 수 있습니다'),
('GM', 'Group', 'Manage', '그룹에 대한 모든 것을 관리합니다'),
('GP', 'Group', 'Proxy', '그룹 명의로 된 메모를 등록할 수 있습니다'),
('GA', 'Group', 'Announce', '그룹 멤버들에게 공지사항을 전달할 수 있습니다'),
('RC', 'Role', 'Create', '그룹 내의 역할을 만들 수 있습니다'),
('RM', 'Role', 'Manage', '그룹 내의 모든 역할을 관리할 수 있습니다'),
('UI', 'User', 'Invite', '그룹에 유저를 초대할 수 있습니다'),
('UB', 'User', 'Ban', '그룹에서 유저를 쫓아내거나 블랙리스트를 관리할 수 있습니다'),
('UG', 'User', 'Grant', '특정 그룹의 멤버와 역할의 연결을 관리할 수 있습니다')