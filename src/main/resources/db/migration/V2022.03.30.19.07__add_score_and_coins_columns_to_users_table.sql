alter table public.users
    add column if not exists score int not null default 0,
    add column if not exists coins int not null default 0;
