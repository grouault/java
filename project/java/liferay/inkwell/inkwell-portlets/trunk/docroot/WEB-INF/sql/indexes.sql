create index IX_2A4C57A3 on Slogan_Slogan (companyId);
create index IX_C13C6AE5 on Slogan_Slogan (groupId);
create index IX_44A7F4CB on Slogan_Slogan (groupId, status);
create index IX_504747C4 on Slogan_Slogan (sloganText);
create index IX_5C972AF on Slogan_Slogan (uuid_);
create index IX_EE255E19 on Slogan_Slogan (uuid_, companyId);
create unique index IX_C17096DB on Slogan_Slogan (uuid_, groupId);