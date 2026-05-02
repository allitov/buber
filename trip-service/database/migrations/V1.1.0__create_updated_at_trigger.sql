create or replace function set_updated_at()
    returns trigger as
$$
begin
    new.updated_at = now();
    return new;
end;
$$ language plpgsql;

------------------------------------------------------------------------------------
create trigger trg_trip_updated_at
    before update
    on trip
    for each row
execute function set_updated_at();